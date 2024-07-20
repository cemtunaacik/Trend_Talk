package com.trendtalk.app.service.impl;

import com.trendtalk.app.repository.interfaces.INotification;
import com.trendtalk.app.repository.entity.Notification;
import com.trendtalk.app.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final INotification notificationRepository;

    @Autowired
    public NotificationServiceImpl(INotification notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}
