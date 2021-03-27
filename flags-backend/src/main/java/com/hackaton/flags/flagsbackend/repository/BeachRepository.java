package com.hackaton.flags.flagsbackend.repository;

import com.hackaton.flags.flagsbackend.model.Beach;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeachRepository extends MongoRepository<Beach, String> {
}
