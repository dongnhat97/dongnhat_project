package com.java.api.controller.user.notification;

import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.entity.Notifications;
import com.java.common.repository.NotificationRepository;
import com.java.common.repository.UserRepository;
import com.java.common.service.BaseService;
import com.java.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService {
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public Page<NotificationsResponses> getAllNotification(Pageable pageable, Integer limit) throws NotFoundException {

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), limit);

        Page<Notifications> notificationsPage = notificationRepository.getAllNotification(super.getCurrentUserId(), pageRequest);
        List<Notifications> notificationsList = notificationsPage.getContent();
        List<NotificationsResponses> notificationResponses = new ArrayList<>();
        for (Notifications notifications : notificationsList) {
            NotificationsResponses notificationsResponse = new NotificationsResponses();
            notificationsResponse.setTitle(notifications.getTitle());
            notificationsResponse.setId(notifications.getId());
            notificationsResponse.setPublicationStart(notifications.getPublicationStart());
            notificationsResponse.setStatus(notifications.getStatus().toString());
            notificationsResponse.setId(notifications.getId());
//                Optional<User> userOptional = userRepository.findById(notifications.getCreatedId());
//                if (userOptional.isPresent()) {
//                    notificationsResponse.setNameUserPost(userOptional.get().getUserName());
//                }
            notificationResponses.add(notificationsResponse);

        }

        return new PageImpl(notificationResponses, pageRequest, notificationsPage.getTotalElements());
    }
}
