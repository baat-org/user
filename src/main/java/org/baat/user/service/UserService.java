package org.baat.user.service;


import org.baat.core.transfer.user.SignupRequest;
import org.baat.core.transfer.user.UserCredentials;
import org.baat.core.transfer.user.UserInfo;
import org.baat.user.repository.UserCredentialsRepository;
import org.baat.user.repository.UserInfoRepository;
import org.baat.user.repository.UserTokenRepository;
import org.baat.user.repository.entity.UserCredentialsEntity;
import org.baat.user.repository.entity.UserInfoEntity;
import org.baat.user.repository.entity.UserTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.baat.user.util.Passwords.getNextSalt;
import static org.baat.user.util.Passwords.hash;
import static org.baat.user.util.Passwords.isExpectedPassword;

@Service
public class UserService {

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	UserCredentialsRepository userCredentialsRepository;

	@Autowired
	UserTokenRepository userTokenRepository;

	public String authenticate(final UserCredentials passedCredentials) {
		if (passedCredentials == null)
			throw new IllegalArgumentException("User Credentials must be provided");

		if (StringUtils.isEmpty(passedCredentials.getUserName()))
			throw new IllegalArgumentException("user name must be provided");

		if (StringUtils.isEmpty(passedCredentials.getPassword()))
			throw new IllegalArgumentException("password must be provided");

		final UserInfoEntity userInfoEntity = userInfoRepository.findByEmail(passedCredentials.getUserName());
		if (userInfoEntity == null)
			throw new IllegalArgumentException("Invalid user name, could not find user with such user name");

		final UserCredentialsEntity existingCredentials = userCredentialsRepository.findByUserId(userInfoEntity.getId());
		if (existingCredentials == null)
			throw new IllegalStateException("No credentials are stored for the user, please signup again");

		if (!isExpectedPassword(passedCredentials.getPassword(),
				existingCredentials.getSalt(),
				existingCredentials.getPasswordHash())) {
			throw new IllegalArgumentException("Invalid username/password");
		}

		final UserTokenEntity userToken = userTokenRepository.save(
				new UserTokenEntity(userInfoEntity.getId(), UUID.randomUUID().toString()));
		return userToken.getUserToken();
	}

	public String signup(final SignupRequest signupRequest) {
		if (signupRequest == null)
			throw new IllegalArgumentException("signupRequest must be provided");

		if (StringUtils.isEmpty(signupRequest.getEmail()))
			throw new IllegalArgumentException("email must be provided");

		if (StringUtils.isEmpty(signupRequest.getName()))
			throw new IllegalArgumentException("name must be provided");

		if (StringUtils.isEmpty(signupRequest.getPassword()))
			throw new IllegalArgumentException("password must be provided");

		if (userInfoRepository.findByEmail(signupRequest.getEmail()) != null)
			throw new IllegalArgumentException("User with same email already exists");

		final UserInfoEntity user = userInfoRepository.save(new UserInfoEntity(signupRequest.getName(),
				signupRequest.getEmail(), signupRequest.getAvatarUrl()));

		final byte[] salt = getNextSalt();
		final byte[] hash = hash(signupRequest.getPassword(), salt);

		userCredentialsRepository.save(
				new UserCredentialsEntity(user.getId(),
						user.getEmail(),
						salt,
						hash));

		final UserTokenEntity userToken = userTokenRepository.save(new UserTokenEntity(user.getId(),
				UUID.randomUUID().toString()));
		return userToken.getUserToken();
	}

	public boolean validateUserToken(final String userToken) {
		if (StringUtils.isEmpty(userToken))
			return false;

		return (userTokenRepository.findByUserToken(userToken) != null);
	}

	public UserInfo getUserForToken(final String userToken) {
		UserTokenEntity userTokenEntity = userTokenRepository.findByUserToken(userToken);
		if (userTokenEntity != null) {
			Optional<UserInfoEntity> userInfoEntityOptional = userInfoRepository.findById( userTokenEntity.getUserId());

			if (userInfoEntityOptional.isPresent()) {
				final UserInfoEntity userInfoEntity = userInfoEntityOptional.get();
				return new UserInfo(userInfoEntity.getId(), userInfoEntity.getEmail(),
						userInfoEntity.getFullName(), userInfoEntity.getAvatarUrl());
			}
		}
		return null;
	}

	public List<UserInfo> getAllUsers() {
		List<UserInfoEntity> userInfoEntities = userInfoRepository.findAll();
		List<UserInfo> userInfos = new ArrayList<>();
		if (userInfoEntities != null) {
			for (final UserInfoEntity userInfoEntity : userInfoEntities) {
				userInfos.add(new UserInfo(userInfoEntity.getId(), userInfoEntity.getEmail(),
						userInfoEntity.getFullName(), userInfoEntity.getAvatarUrl()));
			}
		}
		return userInfos;
	}

	public Set<String> getUserTokens(final Long userId) {
		final Set<UserTokenEntity> userTokens = userTokenRepository.findByUserId(userId);
		if (userTokens == null) {
			return Collections.emptySet();
		}
		return userTokens.stream()
				.map(UserTokenEntity::getUserToken)
				.collect(Collectors.toSet());
	}
}
