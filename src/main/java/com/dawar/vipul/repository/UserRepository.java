package com.dawar.vipul.repository;

import com.dawar.vipul.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
