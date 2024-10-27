package com.user.services.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:8080/department")
public interface APIClient {

    @GetMapping("/getDepartmentById/{id}")
    ResponseEntity<Object> getDepartmentById(@PathVariable("id") Long id);
}
