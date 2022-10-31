package com.java.api.controller.admin.seminarmanager;

import com.java.api.controller.admin.seminarmanager.dto.CreateSeminarDto;
import com.java.api.controller.admin.seminarmanager.dto.ListSeminarResponse;
import com.java.api.controller.admin.seminarmanager.dto.UpdateSeminarDto;
import com.java.common.constant.CommonConstant;
import com.java.common.response.APIErrorResponse;
import com.java.common.response.APIResponse;
import com.java.utils.BindingResultUtils;
import com.java.utils.UtilsData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/seminars")
@RequiredArgsConstructor
public class SeminarController {

    private final String GET_LIST = "/list";
    private final String DETAIL = "/detail";
    private final String CREATE_SEMINAR = "/create";
    private final String UPDATE_SEMINAR = "/update/";
    private final String DELETE_SEMINAR = "/delete/";


    private final SeminarService seminarService;

    private final BindingResultUtils bindingResultUtils;

    @GetMapping(GET_LIST)
    public APIResponse getAll(@PageableDefault(size = 10) Pageable pageable,
                              @RequestParam(defaultValue = "10") Integer limit) {
        Page<ListSeminarResponse> seminarResponses = seminarService.listSeminar(pageable, limit);
        if (seminarResponses.isEmpty()) {
            return APIResponse.okStatus(seminarResponses.getContent());
        }
        return APIResponse.okStatus(UtilsData.pagingResponse(seminarResponses));
    }

    @PostMapping(CREATE_SEMINAR)
    public Object createSeminar(@Valid @RequestBody CreateSeminarDto seminarDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> mapError = bindingResultUtils.errorMapBindingResultUtils(bindingResult);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);

        }
        return this.seminarService.saveSeminar(seminarDto);
    }
    @PatchMapping(UPDATE_SEMINAR+"{id}")
    public Object updateSeminar(@PathVariable Integer id, @Valid @RequestBody UpdateSeminarDto updateSeminarDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> mapError = bindingResultUtils.errorMapBindingResultUtils(bindingResult);
            return APIErrorResponse.errorStatus(CommonConstant.ERROR_NAMES.NG, null, mapError, HttpStatus.BAD_REQUEST);
        }
      return seminarService.updateSeminar(updateSeminarDto);
    }
    @PatchMapping(DELETE_SEMINAR+"{id}")
    public Object deleteSeminar(@PathVariable Integer id) {
        return seminarService.deleteSeminar(id);
    }



}
