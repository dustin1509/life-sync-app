package com.dustin.repository;

import com.dustin.model.entity.SystemUser;
import com.dustin.service.GenericRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends GenericRepository<SystemUser, Long> {
    Optional<SystemUser> findByUsername(String username);

    @Query("SELECT CASE WHEN count(u) = 0 THEN FALSE ELSE TRUE END FROM SystemUser u WHERE lower(u.username) = lower(:username)")
    boolean checkUsernameExist(String username);

    @Cacheable(value = "userId", key = "#id + '::dustin::' ", unless = "#result == null")
    SystemUser getUserById(Long id);

}
