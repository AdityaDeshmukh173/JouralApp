package com.adityaprojectcompany.journalApp.Repository;

import com.adityaprojectcompany.journalApp.Entity.ConfigJournalAppEntity;
import com.adityaprojectcompany.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}
