package com.trendtalk.app.repository.interfaces;

import com.trendtalk.app.repository.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotification extends JpaRepository<Notification, Integer> {
}
