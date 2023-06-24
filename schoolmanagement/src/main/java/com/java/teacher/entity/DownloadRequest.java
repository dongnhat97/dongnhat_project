package com.java.teacher.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DownloadRequest {
    private List<Integer> listTeacherId;
    private String action;
}
