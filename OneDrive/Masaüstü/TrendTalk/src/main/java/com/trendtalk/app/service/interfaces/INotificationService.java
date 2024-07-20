package com.trendtalk.app.service.interfaces;

import com.trendtalk.app.repository.entity.Notification;

public interface INotificationService {
    Notification createNotification(Notification notification);
}
