package com.bot.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by itimofeev on 07.07.2016.
 */
public class MongoDbUserDetails implements UserDetails {

	private String username;

	private String password;

	private List<GrantedAuthority> authorities;

	private Boolean accountNonExpired = true;

	private Boolean accountNonLocked = true;

	private Boolean credentialsNonExpired = true;

	private Boolean enabled = true;

	public MongoDbUserDetails(String username, String password, List<String> roles) {
		this.setUsername(username);
		this.setPassword(password);
		this.setAuthorities(new ArrayList<GrantedAuthority>());
		for (String role : roles) {
			this.authorities.add(new SimpleGrantedAuthority(role));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
