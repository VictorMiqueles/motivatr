package com.davmt.motivatr.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davmt.motivatr.model.NotificationSetting;
import com.davmt.motivatr.repository.NotificationSettingsRepository;
import com.davmt.motivatr.service.UserService;
import com.davmt.motivatr.model.User;

@Service
public class NotificationService {

  @Autowired 
  NotificationSettingsRepository notificationRepository;

  @Autowired
  UserService userService;

  public NotificationSetting createNotificationSetting() {
    NotificationSetting notificationSetting = new NotificationSetting(false, false, false);
    save(notificationSetting);
    return notificationSetting;
  }

  public void save(NotificationSetting notificationSetting) {
    notificationRepository.save(notificationSetting);
  }
  
  public NotificationSetting getNotificationSettingsFromPrincipal(Principal principal) {
    User user = userService.getUserFromPrincipal(principal);
    Long userId = user.getId();
    NotificationSetting notificationSetting = notificationRepository.findByUserId(userId).get(0);
    return notificationSetting;
  }
}
