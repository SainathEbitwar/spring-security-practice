package com.therealsainath.repo;

import com.therealsainath.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDetails, String> {

    Optional<User> findUserByUserId(String userId);

}
