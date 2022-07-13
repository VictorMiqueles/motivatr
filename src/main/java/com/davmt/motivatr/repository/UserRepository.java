package com.davmt.motivatr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.davmt.motivatr.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByUsername(String username);

  List<User> findByUsernameContains(String username);

  Boolean existsByEmail(String email);

  Boolean existsByUsername(String username);

  List<User> findTop10ByOrderByUsersData_TotalCompletedDesc();

  List<User> findByNotificationSetting_TextNotificationsIsTrue();

}
