package com.hackaton.flags.flagsbackend;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.hackaton.flags.flagsbackend.repository")
public class MongoConfig  extends AbstractMongoClientConfiguration {


    @Override
    protected String getDatabaseName() {
        return "flags";
    }

    @Override
    public MongoClient mongoClient(){
        ConnectionString connectionString = new ConnectionString("mongodb://mnp160flags:admin123@cluster0-shard-00-00.rutbx.mongodb.net:27017,cluster0-shard-00-01.rutbx.mongodb.net:27017,cluster0-shard-00-02.rutbx.mongodb.net:27017/flags?ssl=true&replicaSet=atlas-ocymr8-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(settings);
    }


}
