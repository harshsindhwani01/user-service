package com.user.services.service;

import com.user.services.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> addUser(User user);

    ResponseEntity<Object> findById(Long id);
}
