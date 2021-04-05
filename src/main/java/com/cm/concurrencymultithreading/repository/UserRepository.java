package com.cm.concurrencymultithreading.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cm.concurrencymultithreading.beans.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
