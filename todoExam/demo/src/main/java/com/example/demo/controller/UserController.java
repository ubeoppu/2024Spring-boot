package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup") //회원가입
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDto) {
       try{

           if(userDto == null || userDto.getPassword() == null){
               throw new RuntimeException("Invalid Password Value");
           }
           UserEntity userEntity = UserEntity.builder()
                   .username(userDto.getUsername())
                   .password(userDto.getPassword())
                   .build();

           UserEntity registerdUser = userService.create(userEntity);

           UserDTO responseUserDTO = UserDTO.builder()
                   .id(registerdUser.getId())
                   .username(registerdUser.getUsername())
                   .build();

           return ResponseEntity.ok(responseUserDTO);

       }catch(Exception e){
           ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();

           return ResponseEntity.badRequest().body(responseDTO);
       }

    }

    @PostMapping("signin")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDto) {
        UserEntity user = userService.getByCredentials(userDto.getUsername(), userDto.getPassword());

        if(user != null){
            UserDTO responseUserDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .id(user.getId())
                    .build();

            return ResponseEntity.ok(responseUserDTO);
        }else{
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login Error")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
