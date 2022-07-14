package com.davmt.motivatr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.service.UserService;
import com.davmt.motivatr.model.User;

@Controller
public class UsersController {

  @Autowired
  UserService userService;

  @GetMapping("/users/new")
  public String signup(Model model) {
    model.addAttribute("user", new User());
    return "users/new";
  }

  @PostMapping("/users")
  public RedirectView signup(@ModelAttribute User userForm, RedirectAttributes redirAttrs) {
    if (!userService.validateUserDetails(userForm)) {
      redirAttrs.addFlashAttribute("message", userService.getStatusMessage());
      return new RedirectView("/users/new");
    }

    userService.createUser(userForm);

    return new RedirectView("/login");
  }
}
