package com.karantech.java.product_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.karantech.java.product_service.entity.UserInfo;
import com.karantech.java.product_service.entity.UserInfoUserDetails;
import com.karantech.java.product_service.repository.UserRepository;

public class UserInfouserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> UserInfo = userRepo.findByUserName(username);
		return UserInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));

	}

}
