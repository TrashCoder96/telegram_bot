package com.bot.config;

import com.bot.data.repository.AccountRepository;
import com.bot.data.repository.ComparisonRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by itimofeev on 16.06.2016.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = {AccountRepository.class, ComparisonRepository.class })
public class DatabaseConfig extends AbstractMongoConfiguration {

	@Value("${spring.application.name}")
	private String proAppName;

	@Value("${spring.data.mongodb.host}")
	private String mongoHost;

	@Value("${spring.data.mongodb.port}")
	private String mongoPort;

	@Value("${spring.data.mongodb.database}")
	private String mongoDB;

	@Value("${spring.data.mongodb.login}")
	private String mongoDBLogin;

	@Value("${spring.data.mongodb.password}")
	private String mongoDBPassword;

	@Override
	protected String getDatabaseName() {
		return mongoDB;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoHost + ":" + mongoPort);
	}
}
