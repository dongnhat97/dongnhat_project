package com.java.api.controller.admin.notificationmanager;

import com.java.api.controller.admin.notificationmanager.dto.NotificationDto;
import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.constant.CommonConstant;
import com.java.common.entity.Notifications;
import com.java.common.entity.User;
import com.java.common.entity.UserNotifications;
import com.java.common.repository.NotificationRepository;
import com.java.common.repository.UserNotificationRepository;
import com.java.common.repository.UserRepository;
import com.java.common.response.APIErrorResponse;
import com.java.enums.CommonEnum;
import com.java.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    public Page<NotificationsResponses> getAllNotification(Pageable pageable,
                                                           Integer limit, String title, String status,
                                                           String fromPublicationStart, String toPublicationStart,
                                                           String toPublicationEnd, String fromPublicationEnd) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), limit, Sort.by(Sort.Direction.DESC, "publication_start"));
        PageRequest pageableRequest = PageRequest.of(pageable.getPageNumber(), limit);
        if (title.equals("") && status.equals("") && fromPublicationStart.equals("") && toPublicationStart.equals("") &&
                fromPublicationEnd.equals("") && toPublicationEnd.equals("")) {
            return convertNotificationPage(notificationRepository.findAllByStatusOrderByPublicationStartDesc(CommonEnum.StatusEnum.ACTIVE, pageableRequest));
        } else {
            switch (status) {
                case CommonConstant.STATUS.POSTING:
                    return convertNotificationPage(notificationRepository.findAllByPosting(title, fromPublicationStart, toPublicationStart, toPublicationEnd, fromPublicationEnd, pageRequest));
                case CommonConstant.STATUS.BEFORE_POSTING:
                    return convertNotificationPage(notificationRepository.findAllBeforePosting(title, fromPublicationStart, toPublicationStart, toPublicationEnd, fromPublicationEnd, pageRequest));
                case CommonConstant.STATUS.AFTER_POSTING:
                    return convertNotificationPage(notificationRepository.findAllAfterPosting(title, fromPublicationStart, toPublicationStart, toPublicationEnd, fromPublicationEnd, pageRequest));
                default:
                    return convertNotificationPage(notificationRepository.findAllByStatusAndTitleContainingOrderByPublicationStartDesc(CommonEnum.StatusEnum.ACTIVE,title, pageableRequest));

            }
        }

    }


    public Page<NotificationsResponses> convertNotificationPage(Page<Notifications> notificationsPage) {

        List<Notifications> notificationsList = notificationsPage.getContent();
        List<NotificationsResponses> notificationsResponsesList = new ArrayList<>();
        for (Notifications notifications : notificationsList) {
            NotificationsResponses notificationsResponses = new NotificationsResponses();
            notificationsResponses.setId(notifications.getId());
            notificationsResponses.setStatus(notifications.getStatus().toString());
            notificationsResponses.setPublicationStart(notifications.getPublicationStart());
            notificationsResponses.setTitle(notifications.getTitle());
            if (notifications.createdId != null) {
                Optional<User> userOptional = userRepository.findById(notifications.createdId);
                if (userOptional.isPresent()) {
                    notificationsResponses.setNameUserPost(userOptional.get().getUserName());
                } else {
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
        return new PageImpl<>(notificationsResponsesList, notificationsPage.getPageable(), notificationsPage.getTotalElements());
    }


    public NotificationDto updateNotifications(Integer id, NotificationDto notificationDto) {
        Optional<Notifications> notificationsOptional = notificationRepository.findById(id);
        BeanUtils.copyProperties(notificationDto,notificationsOptional);
        notificationsOptional.get().setPublicationStart(DateTimeUtils.convertStringToLocalDateTime(notificationDto.getPublicationStart(),CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        notificationsOptional.get().setPublicationEnd(DateTimeUtils.convertStringToLocalDateTime(notificationDto.getPublicationEnd(),CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        notificationsOptional.get().setStatus(CommonEnum.StatusEnum.ACTIVE);
        Notifications notificationsReturn = notificationRepository.save(notificationsOptional.get());
        List<Integer> listIdUser = notificationDto.getListIdUser();
        List<Integer>listIdUserDB = userNotificationRepository.getUserIdByNotificationId(id);
        if (!listIdUser.equals(listIdUserDB)) {
            for (Integer userId: listIdUser) {
                if (!listIdUserDB.contains(userId)){
                    Optional<User> userOptional = userRepository.findById(userId);
                    UserNotifications userNotifications = new UserNotifications();
                    if (userOptional.isPresent()) {
                        userNotifications.setUser(userOptional.get());
                        userNotifications.setNotifications(notificationsReturn);
                    }
                }
            }for (Integer userIdDB: listIdUserDB){
                if (!listIdUser.contains(userIdDB)) {
                    Optional<User> userOptionalDB = userRepository.findById(id);
                    if (userOptionalDB.isPresent()) {
                        Optional<UserNotifications> userNotifications = userNotificationRepository.findByUserAndNotificationsAndStatus(userOptionalDB.get(),notificationsReturn, CommonEnum.StatusEnum.ACTIVE);
                       if (userNotifications.isPresent()) {
                           userNotifications.get().setStatus(CommonEnum.StatusEnum.DELETED);
                           userNotificationRepository.save(userNotifications.get());
                       }
                    }
                }
            }
        }
     BeanUtils.copyProperties(notificationsReturn,notificationDto);
        return notificationDto;

    }
}
