package com.java.utils;

import com.java.common.page.PageInfo;
import org.springframework.data.domain.Page;

public class UtilsData {

    public static <T> PageInfo<T> pagingResponse(Page<T> page) {
             PageInfo pageInfo = new PageInfo();
             pageInfo.setTotal(page.getTotalElements());
             pageInfo.setLimit(page.getSize());
             pageInfo.setPages(page.getTotalPages());
             pageInfo.setPage(page.getNumber()+1);
             pageInfo.setResult(page.getContent());
             return pageInfo;
    }
}
