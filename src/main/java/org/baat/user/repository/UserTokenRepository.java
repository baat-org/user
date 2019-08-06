package org.baat.user.repository;

import org.baat.user.repository.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
	Set<UserTokenEntity> findByUserId( Long userId );

	UserTokenEntity findByUserToken( String userToken );
}
