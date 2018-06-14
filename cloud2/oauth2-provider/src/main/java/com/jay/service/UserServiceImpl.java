package com.jay.service;


import com.jay.domain.User;
import com.jay.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	//private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserDao userDao;

	@Override
	public void create(User user) {

		User existing = userDao.findByUsername(user.getUsername());
		//Assert.isNull(existing, "user already exists: " + user.getUsername());

		String hash = "{bcrypt}"+encoder.encode(user.getPassword());
		user.setPassword(hash);
		userDao.save(user);

	}

	@Override
	public User getUser(Long userId) {
		return userDao.getOne(userId);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByName(String username) {
		return userDao.findByUsername(username);
	}
}
