package com.java.api.controller.user.notification;

import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.entity.Notifications;
import com.java.common.repository.NotificationRepository;
import com.java.common.response.APIResponse;
import com.java.common.service.BaseService;
import com.java.exception.NotFoundException;
import com.java.utils.UtilsData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController extends BaseService {
    private final NotificationRepository notificationRepository;
    private final UserServiceImpl userServiceImpl;


    @GetMapping("/notification")
    public APIResponse getListNotification(@PageableDefault Pageable pageable,
                                           @RequestParam(defaultValue = "10") Integer limit) throws NotFoundException {

        Page<NotificationsResponses> notificationsPage = this.userServiceImpl.getAllNotification(pageable, limit);
        if (notificationsPage.isEmpty()) {
            return APIResponse.okStatus(notificationsPage.getContent());
        }
        return APIResponse.okStatus(UtilsData.pagingResponse(notificationsPage));
    }
}
