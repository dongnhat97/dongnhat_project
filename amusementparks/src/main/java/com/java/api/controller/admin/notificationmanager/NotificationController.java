package com.java.api.controller.admin.notificationmanager;

import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.entity.Notifications;
import com.java.common.response.APIResponse;
import com.java.utils.UtilsData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private  final String GET_ALL = "/getAll";
    private  final  String UPDATE_NOTIFICATION =  " /notification";
    private NotificationService notificationService;

    @GetMapping(GET_ALL)
    public APIResponse getAllNotifications( Pageable pageable,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(name = "from_publication_start",defaultValue = "") String fromPublicationStart,
            @RequestParam(name = "to_publication_start",defaultValue = "") String  toPublicationStart ,
            @RequestParam(name = "to_publication_end",defaultValue = "") String toPublicationEnd,
            @RequestParam(name = "from_publication_end",defaultValue = "") String fromPublicationEnd) {
        Page<NotificationsResponses> notificationsPage = notificationService.getAllNotification(pageable,
                limit,title,status,fromPublicationStart,toPublicationStart,toPublicationEnd,fromPublicationEnd);
        if (notificationsPage.isEmpty()) {
            return APIResponse.okStatus(notificationsPage.getContent());
        }
        return APIResponse.okStatus(UtilsData.pagingResponse(notificationsPage));
    }

}
