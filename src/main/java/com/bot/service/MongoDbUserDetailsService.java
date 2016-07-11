package com.bot.service;

import com.bot.data.Account;
import com.bot.data.repository.AccountRepository;
import com.bot.security.MongoDbUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by itimofeev on 07.07.2016.
 */
public class MongoDbUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		MongoDbUserDetails mongoDbUserDetails = new MongoDbUserDetails(account.getEmail(), account.getPassword(), account.getRoles());
		return mongoDbUserDetails;
	}
}
