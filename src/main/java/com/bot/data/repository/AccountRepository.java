package com.bot.data.repository;

import com.bot.data.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by itimofeev on 07.07.2016.
 */

public interface AccountRepository extends MongoRepository<Account, String> {

    public Account findByEmail(String email);

}
