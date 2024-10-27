package com.user.services.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.services.dto.DepartmentDTO;
import com.user.services.dto.ResponseDTO;
import com.user.services.dto.UserDTO;
import com.user.services.entity.User;
import com.user.services.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.runtime.ObjectMethods;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    APIClient apiClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<Object> addUser(User user) {
        ResponseDTO response = new ResponseDTO();
        try {
            userRepo.save(user);
            response.setMessage("User saved successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error in saving User!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            //ResponseEntity<Object> responseEntity = null;
            ResponseEntity<DepartmentDTO> responseEntity = null;
            DepartmentDTO departmentDTO = null;
            try {
                //this is used for feign client
                //responseEntity = apiClient.getDepartmentById(user.get().getDepartmentId());

                //this is used for eruka server client
                responseEntity = restTemplate
                        .getForEntity("http://department-service/department/getDepartmentById/" + user.get().getDepartmentId(),
                                DepartmentDTO.class);
                Object responseBody = responseEntity.getBody();
                ObjectMapper mapper = new ObjectMapper();
                departmentDTO = mapper.convertValue(responseBody, DepartmentDTO.class);
                System.out.println(responseBody);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            UserDTO userDTO = new UserDTO();
            userDTO.setDepartment(departmentDTO);
            userDTO.setId(user.get().getId());
            userDTO.setEmail(user.get().getEmail());
            userDTO.setFirstName(user.get().getFirstName());
            userDTO.setLastName(user.get().getLastName());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            ResponseDTO response = new ResponseDTO();
            response.setMessage("No record found!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
