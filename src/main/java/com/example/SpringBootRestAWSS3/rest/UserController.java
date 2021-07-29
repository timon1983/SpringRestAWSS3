package com.example.SpringBootRestAWSS3.rest;

import com.example.SpringBootRestAWSS3.model.User;
import com.example.SpringBootRestAWSS3.service.EventService;
import com.example.SpringBootRestAWSS3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;
    private EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("user")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<User> getUser(){
        User user = userService.findByName("user");
        if(user == null){
            return  new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setEvents(eventService.findAllByUser(user.getId()));

        return new ResponseEntity<User>(user , HttpStatus.OK);
    }

    @GetMapping("{name}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<User> getUser(@PathVariable("name") String name){
        User user = userService.findByName(name);
        if(user == null){
            return  new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setEvents(eventService.findAllByUser(user.getId()));

        return new ResponseEntity<User>(user , HttpStatus.OK);
    }
}
