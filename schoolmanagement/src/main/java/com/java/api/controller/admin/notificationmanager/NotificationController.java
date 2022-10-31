package com.java.api.controller.admin.notificationmanager;

import com.java.api.controller.admin.notificationmanager.dto.NotificationDto;
import com.java.api.controller.admin.notificationmanager.dto.NotificationsResponses;
import com.java.common.constant.CommonConstant;
import com.java.common.response.APIErrorResponse;
import com.java.common.response.APIResponse;
import com.java.utils.BindingResultUtils;
import com.java.utils.UtilsData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
    private static final String GET_ALL = "/getAll";
    private static final String UPDATE_NOTIFICATION = " /notification";
    private final NotificationService notificationService;
    private final BindingResultUtils bindingResultUtils;

    @GetMapping(GET_ALL)
    public APIResponse getAllNotifications(Pageable pageable,
                                           @RequestParam(defaultValue = "10") Integer limit,
                                           @RequestParam(defaultValue = "") String title,
                                           @RequestParam(defaultValue = "") String status,
                                           @RequestParam(name = "from_publication_start", defaultValue = "") String fromPublicationStart,
                                           @RequestParam(name = "to_publication_start", defaultValue = "") String toPublicationStart,
                                           @RequestParam(name = "to_publication_end", defaultValue = "") String toPublicationEnd,
                                           @RequestParam(name = "from_publication_end", defaultValue = "") String fromPublicationEnd) {
        Page<NotificationsResponses> notificationsPage = notificationService.getAllNotification(pageable,
                limit, title, status, fromPublicationStart, toPublicationStart, toPublicationEnd, fromPublicationEnd);
        if (notificationsPage.isEmpty()) {
            return APIResponse.okStatus(notificationsPage.getContent());
        }
        return APIResponse.okStatus(UtilsData.pagingResponse(notificationsPage));
    }

    @PatchMapping(UPDATE_NOTIFICATION + "{id}")
    public APIErrorResponse updateNotifications(@Valid @PathVariable Integer id,
                                                @RequestBody NotificationDto notificationDto,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> mapError = bindingResultUtils.errorMapBindingResultUtils(bindingResult);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);
        }
        NotificationDto notificationDtoReturn = notificationService.updateNotifications(id, notificationDto);
        return APIErrorResponse.createdStatus(CommonConstant.ERROR_NAMES.OK, notificationDtoReturn, null, HttpStatus.OK);

    }

}
