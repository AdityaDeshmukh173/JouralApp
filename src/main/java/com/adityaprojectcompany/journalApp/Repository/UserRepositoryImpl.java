package com.adityaprojectcompany.journalApp.Repository;

import com.adityaprojectcompany.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate ;

    public List<User> getUsersForSA(){
        Query query = new Query();

/*              This is Automatically an AND condition for both Queries */
/*          Rather than this Query we can add a check for regular expression for an Email
        query.addCriteria(Criteria.where("email").exists(true)) ;
        query.addCriteria(Criteria.where("email").ne(null).ne("")) ;    */

        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) ;
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true)) ;

/*        to make it OR condition it can be done as follow
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.orOperator(
                Criteria.where("email").exists(true) ,
                Criteria.where("sentimentAnalysis").is(true))) ;    */

//        query.addCriteria(Criteria.where("nameName").is("Vipul")) ;         // User with name Vipul
//        query.addCriteria(Criteria.where("field").ne("notequalstothis"));       // We can add any numbers of Criterias

        List<User> users = mongoTemplate.find(query, User.class);       // class for where to perform that Query
        return users ;
    }
}
