package com.java.api.controller.admin.notificationmanager;

import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.constant.CommonConstant;
import com.java.common.entity.Notifications;
import com.java.common.entity.User;
import com.java.common.entity.UserNotifications;
import com.java.common.repository.NotificationRepository;
import com.java.common.repository.UserNotificationRepository;
import com.java.common.repository.UserRepository;
import com.java.enums.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;
    private UserNotificationRepository userNotificationRepository;

    public Page<NotificationsResponses> getAllNotification (Pageable pageable,
                                                            Integer limit, String title, String status,
                                                            String fromPublicationStart, String toPublicationStart,
                                                            String toPublicationEnd, String fromPublicationEnd) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),limit, Sort.by(Sort.Direction.DESC,"publication_start"));
        PageRequest pageableRequest = PageRequest.of(pageable.getPageNumber(),limit);
        if (title.equals("") && status.equals("") && fromPublicationStart.equals("") && toPublicationStart.equals("") &&
                fromPublicationEnd.equals("") && toPublicationEnd.equals("")) {
           return convertNotificationPage(notificationRepository.findAllByStatusOrderByPublicationStartDesc(CommonEnum.StatusEnum.ACTIVE,pageableRequest));
        }
        else {
            switch (status) {
                case CommonConstant.STATUS.POSTING:
                    return convertNotificationPage(notificationRepository.findAllByPosting(title,fromPublicationStart,toPublicationStart,toPublicationEnd,fromPublicationEnd,pageRequest));
                case CommonConstant.STATUS.BEFORE_POSTING:
                    return convertNotificationPage(notificationRepository.findAllBeforePosting(title,fromPublicationStart,toPublicationStart,toPublicationEnd,fromPublicationEnd,pageRequest));
                case CommonConstant.STATUS.AFTER_POSTING:
                    return convertNotificationPage(notificationRepository.findAllAfterPosting(title,fromPublicationStart,toPublicationStart,toPublicationEnd,fromPublicationEnd,pageRequest));
                default:
                    return convertNotificationPage(notificationRepository.findAllByStatusAndTitleContainingOderBYPublicationStartDesc(CommonEnum.StatusEnum.ACTIVE,pageableRequest));

            }
        }
    }


    public Page<NotificationsResponses> convertNotificationPage(Page<Notifications> notificationsPage){

        List<Notifications> notificationsList = notificationsPage.getContent();
        List<NotificationsResponses> notificationsResponsesList = new ArrayList<>();
        for (Notifications notifications : notificationsList) {
            NotificationsResponses notificationsResponses = new NotificationsResponses();
            notificationsResponses.setId(notifications.getId());
            notificationsResponses.setStatus(notifications.getStatus().toString());
            notificationsResponses.setPublicationStart(notifications.getPublicationStart());
            notificationsResponses.setTitle(notifications.getTitle());
                 if (notifications.createdId!=null) {
                     Optional<User> userOptional = userRepository.findById(notifications.createdId);
                     if (userOptional.isPresent()) {
                         notificationsResponses.setNameUserPost(userOptional.get().getFirstName()+ " " +userOptional.get().getLastName());
                     }else {
                         notificationsResponses.setNameUserPost("");
                     }
                 }
            /**
             * total users receiving notifications
             */
            List<UserNotifications> userNotificationsList =
                    userNotificationRepository.findAllByStatusAndNotifications(CommonEnum.StatusEnum.ACTIVE, notifications);
            notificationsResponses.setTotalUser(userNotificationsList.size());
            notificationsResponsesList.add(notificationsResponses);
        }
//        return new PageImpl<>(notificationsResponsesList,notificationsPage.getPageable(),notificationsPage.getSize());
        return new PageImpl<>(notificationsResponsesList, notificationsPage.getPageable(), notificationsPage.getTotalElements());
    }


}
