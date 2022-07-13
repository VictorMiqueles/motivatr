package com.davmt.motivatr.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.Authority;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.model.UsersData;
import com.davmt.motivatr.repository.AuthoritiesRepository;
import com.davmt.motivatr.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UsersDataService usersDataService;
  @Autowired
  AuthoritiesRepository authoritiesRepository;
  @Autowired
  PasswordEncoder getPasswordEncoder;

  private String statusMessage;

  private void save(User user) {
    userRepository.save(user);
  }

  public Boolean validateUserDetails(User userForm) {
    if (userRepository.existsByEmail(userForm.getEmail())) {
      statusMessage = "Email already exists!";
      return false;
    }

    if (userRepository.existsByUsername(userForm.getUsername())) {
      statusMessage = "Username taken!";
      return false;
    }

    if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
      statusMessage = "Passwords don't match!";
      return false;
    }
    return true;
  }

  public void createUser(User user) {
    user.setPassword(getPasswordEncoder.encode(user.getPassword()));
    UsersData usersData = usersDataService.createUsersData();

    user.setUsersData(usersData);
    save(user);

    Authority authority = new Authority(user.getUsername(), "ROLE_USER");
    authoritiesRepository.save(authority);
  }

  public User getUserFromPrincipal(Principal principal) {
    List<User> users = userRepository.findByUsername(principal.getName());
    return users.get(0);
  }

  public User getUserByUsername(String username) {
    List<User> users = userRepository.findByUsername(username);
    return users.get(0);
  }

  public List<User> getTopTenUsers() {
    List<User> users = userRepository.findTop10ByOrderByUsersData_PointsDesc();
    return users;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void updateUser(User updateUser) {
    User user = getUserByUsername(updateUser.getUsername());
    if (updateUser.getPassword() != null) {
      user.setPassword(updateUser.getPassword());
    } else if (updateUser.getFirstName() != null) {
    user.setFirstName(updateUser.getFirstName());
    } else if (updateUser.getLastName() != null) {
    user.setLastName(updateUser.getLastName());
    } else if (updateUser.getImageUrl() != null) {
    user.setImageUrl(updateUser.getImageUrl());
    } else if (updateUser.getMobile() != null) {
    user.setMobile(updateUser.getMobile());
    }
    save(user);
  }
}