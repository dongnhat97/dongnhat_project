package com.java.api.controller.admin.seminarmanager;

import com.java.api.controller.admin.seminarmanager.dto.CreateSeminarDto;
import com.java.api.controller.admin.seminarmanager.dto.ListSeminarResponse;
import com.java.api.controller.admin.seminarmanager.dto.UpdateSeminarDto;
import com.java.common.constant.CommonConstant;
import com.java.common.entity.Seminar;
import com.java.common.entity.UserSeminar;
import com.java.common.repository.SeminarRepository;
import com.java.common.repository.UserSeminarRepository;
import com.java.common.response.APIErrorResponse;
import com.java.common.service.MessageService;
import com.java.enums.CommonEnum;
import com.java.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SeminarService {
    private SeminarRepository seminarRepository;
    private MessageService messageService;
    private UserSeminarRepository userSeminarRepository;


    public Page listSeminar(Pageable pageable, Integer limit) {
        Page<Seminar> seminarPage = seminarRepository.findAll(pageable);
        List<Seminar> seminarList = seminarPage.getContent();
        List<ListSeminarResponse> seminarResponses = new ArrayList<>();
        for (Seminar seminars : seminarList) {
            ListSeminarResponse seminarResponse = new ListSeminarResponse();
            seminarResponse.setTitle(seminars.getTitle());
            seminarResponse.setDescription(seminars.getDescription());
            seminarResponse.setLink(seminars.getLink());
            seminarResponse.setStatus(seminars.getStatus());
            seminarResponse.setStartDate(seminars.getStartDate());
            seminarResponse.setEndDate(seminars.getEndDate());
            seminarResponses.add(seminarResponse);
        }
        return new PageImpl<>(seminarResponses, pageable, seminarPage.getTotalElements());
    }

    public Object saveSeminar(CreateSeminarDto seminarDto) {

        Seminar seminar = new Seminar();
        BeanUtils.copyProperties(seminarDto, seminar);
        seminar.setStatus(CommonEnum.StatusEnum.ACTIVE);
        seminar.setStartDate(DateTimeUtils.convertStringToLocalDateTime(seminarDto.startDate, CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        seminar.setEndDate(DateTimeUtils.convertStringToLocalDateTime(seminarDto.endDate, CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        seminarRepository.save(seminar);

        return APIErrorResponse.createdStatus(CommonConstant.ERROR_NAMES.NG, null, null, HttpStatus.OK);

    }

    public Object updateSeminar(UpdateSeminarDto updateSeminarDto) {
        Optional<Seminar> seminar = seminarRepository.findById(updateSeminarDto.getId());
        Map<String, String> mapError = new HashMap<>();
        String code = "";
        String message;
        String result;
        if (!seminar.isPresent()) {
            code = CommonConstant.ERROR_CODES.E_013;
            message = messageService.buildMessages(code);
            result = MessageFormat.format(message, "ID");
            mapError.put("code", code);
            mapError.put("message", message);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(updateSeminarDto, seminar);
        seminar.get().setStatus(CommonEnum.StatusEnum.ACTIVE);
        seminar.get().setStartDate(DateTimeUtils.convertStringToLocalDateTime(updateSeminarDto.getStartDate(), CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        seminar.get().setEndDate(DateTimeUtils.convertStringToLocalDateTime(updateSeminarDto.getEndDate(), CommonConstant.REGEX_PATTERN.REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH));
        seminarRepository.save(seminar.get());
        return APIErrorResponse.createdStatus(CommonConstant.ERROR_NAMES.OK, null, null, HttpStatus.OK);
    }

    public Object deleteSeminar(Integer id) {
        Optional<Seminar> seminar = seminarRepository.findById(id);
        Map<String, String> mapError = new HashMap<>();
        String code = "";
        String message;
        String result;
        if (!seminar.isPresent()) {
            code = CommonConstant.ERROR_CODES.E_013;
            message = messageService.buildMessages(code);
            result = MessageFormat.format(message, "ID");
            mapError.put("code", code);
            mapError.put("message", message);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);
        }
        seminar.get().setStatus(CommonEnum.StatusEnum.DELETED);
        seminarRepository.save(seminar.get());
        Optional<UserSeminar> userSeminar = userSeminarRepository.findUserSeminarBySeminar_Id(id);
        if (userSeminar.isPresent()) {
            userSeminarRepository.deleteById(userSeminar.get().getId());
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.OK, null, null, HttpStatus.OK);
        }
        userSeminar.get().setStatus(CommonEnum.StatusEnum.DELETED);
        userSeminarRepository.save(userSeminar.get());
        return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.OK, null, null, HttpStatus.OK);

    }
}
