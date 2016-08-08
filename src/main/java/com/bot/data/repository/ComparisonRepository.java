package com.bot.data.repository;

import com.bot.data.Comparison;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by asus-pc on 08.08.2016.
 */
public interface ComparisonRepository extends MongoRepository<Comparison, String> {

    public Comparison findByRequest(String request);

}
