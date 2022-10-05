package com.java.common.repository;

import com.java.common.entity.Notifications;
import com.java.common.entity.UserNotifications;
import com.java.enums.CommonEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotifications,Integer> {
    List<UserNotifications> findAllByStatusAndNotifications(CommonEnum.StatusEnum status , Notifications notifications);
}
