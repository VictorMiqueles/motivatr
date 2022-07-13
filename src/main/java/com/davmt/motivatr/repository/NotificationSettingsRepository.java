package com.davmt.motivatr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.davmt.motivatr.model.NotificationSetting;

public interface NotificationSettingsRepository extends CrudRepository<NotificationSetting, Long> {
  List<NotificationSetting> findByUserId(Long userId);
}