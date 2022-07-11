package com.davmt.motivatr.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.davmt.motivatr.model.Challenge;
import com.davmt.motivatr.model.CompletedChallenge;
import com.davmt.motivatr.model.User;
import com.davmt.motivatr.repository.CompletedChallengeRepository;
import com.davmt.motivatr.repository.UserRepository;
import com.davmt.motivatr.service.ChallengeService;
import com.davmt.motivatr.service.CompletedChallengeService;
import com.davmt.motivatr.service.UserService;

@Controller
public class CompletedChallengesController {

    @Autowired
    CompletedChallengeService completedChallengeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompletedChallengeRepository completedChallengeRepository;

    @Autowired
    UserService userService;

    // A few observations:
    //
    // I would do it using a toggle method, ie a method that checks if done and does whatever is appropriate. You 
    // could (read should) move it to the service layer and there you can break it down further if needed.
    //
    // I would probably do it with a @GetMapping as this would save you having to create a form in the template.
    // (basically, you just want to call this method when the button is pushed and in this case you are not passing
    // anything to this method. From the thymeleaf side you could do that with a link styled as a button (give it a class
    // of btn) or a <button> with onClick attribute.
    //
    // On the 'not passing anything', I would probably pass the id of the challenge shown when the button is pressed
    // because imagine that you are one of those who like to do things late at night and you start the challenge at
    // 11:58pm and finish it 12:05am. If you call the getTodaysChallenge you'd get the new challenge and not the one
    // you wanted to mark as done. So, I would pass the id in the URL, ie: /completed/{id} and use that to get
    // the challenge.
    // 
    // So, this toggle method would look something like:
    // 
    // Check if challenge has been completed.
    // If it is:
    //      Delete row from challenges_completions
    //      return false (<-- this is of type Boolean, not String)
    // If it is not:
    //      Add row to challenges_completions
    //      return true
    //
    // On the template side do something like:
    // <button th:attr="class=${isDone ? 'disabled-btn' : 'btn'}">Mark As Done</button>
    // or probably, since you want to toggle it, you could do a th:text="${isDone ? 'Mark as Not Done' : 'Mark As Done'}
    // 
    // Also, you could have a div with a th:if that appears only when isDone == true (and you could style it as a 
    // watermark to appear on top of the challenge div
    
    // Sorry for the mini essay! Hope it makes some sense.

    @GetMapping("/completedChallenges/{id}")
    public RedirectView listCompletedChallenges(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("checkIfChallengeDone", completedChallengeService.checkIfChallengeDone(principal, id));
        return new RedirectView("home");
    }
    
}
