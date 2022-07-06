package com.davmt.motivatr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.repository.AuthoritiesRepository;
import com.davmt.motivatr.repository.UserRepository;
import com.davmt.motivatr.model.Authority;
import com.davmt.motivatr.model.User;

import java.security.Principal;

@Controller
public class UsersController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder getPasswordEncoder;
  @Autowired
  AuthoritiesRepository authoritiesRepository;

  @GetMapping("/users/new")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "users/new";
  }

  @PostMapping("/users")
  public RedirectView signup(@ModelAttribute User user, RedirectAttributes redirAttrs) {
    // TODO: validation class?
    // TODO: move logic etc to a service class
    // TODO: check username is unique
    // TODO: check email is unique
    // TODO: check passwords match

    if (userRepository.existsByEmail(user.getEmail())) {
      redirAttrs.addFlashAttribute("message", "Email already exists!");
      return new RedirectView("/users/new");
    }

    user.setPassword(getPasswordEncoder.encode(user.getPassword()));
    user.setUsername(user.getUsername());// chaange this to username not email!

    userRepository.save(user);

    Authority authority = new Authority(user.getUsername(),
        "ROLE_USER");
    authoritiesRepository.save(authority);
    return new RedirectView("/login");
  }

  @GetMapping("/users/all")
  public String all(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "users/all";
  }

  @GetMapping("users/settings")
  public String settings(Model model, Principal principal) {
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    model.addAttribute("image", currentUser.getImageUrl());
    return "users/settings";
  }

  @GetMapping("/users/editDetails")
  public String showDetails(Model model, Principal principal) {
    User currentUser = userRepository.findByUsername(principal.getName()).get(0);
    model.addAttribute("user", currentUser);
    return "users/editDetails";
  }

  @GetMapping("/users/search")
  public String showSearchResults(Model model, String keyword) {
    List<User> users = userRepository.findByUsernameContains(keyword);
    model.addAttribute("users", users);
    return "users/search";
  }

  @GetMapping("/dashboard")
  public String dashboard() {
    return "users/dashboard";
  }
}
