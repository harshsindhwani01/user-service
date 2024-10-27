package com.user.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DepartmentDTO {

    @JsonIgnore
    private Long id;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}
