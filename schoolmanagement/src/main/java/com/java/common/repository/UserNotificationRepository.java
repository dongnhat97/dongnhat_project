package com.java.common.repository;

import com.java.common.entity.Notifications;
import com.java.common.entity.User;
import com.java.common.entity.UserNotifications;
import com.java.enums.CommonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotifications,Integer> {
    List<UserNotifications> findAllByStatusAndNotifications(CommonEnum.StatusEnum status , Notifications notifications);
    @Query(value = " SELECT user_notifications.user_id"+
                   " FROM user_notifications"+
                   " WHERE user_notification.notifications_id = ?1"+
                   " AND t_user_notifications.status = 0;"
                         ,nativeQuery = true)
    List<Integer> getUserIdByNotificationId(Integer notificationId);

    Optional<UserNotifications> findByUserAndNotificationsAndStatus(User user, Notifications notification, CommonEnum.StatusEnum status);
}
