package com.adityaprojectcompany.journalApp.Repository;

import com.adityaprojectcompany.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;

@ComponentScan
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName) ;

    void deleteByUserName(String userName);
}