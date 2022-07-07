package com.davmt.motivatr.controller;

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
import com.davmt.motivatr.service.UserService;
import com.davmt.motivatr.model.Authority;
import com.davmt.motivatr.model.User;

import java.security.Principal;

@Controller
public class ProfileController {
  @Autowired
  UserService userService;

  @GetMapping("/users/profile")
  public String profile(Model model, Principal principal) {
    model.addAttribute("principal", userService.getUserFromPrincipal(principal));
    return "profile";
  }

}