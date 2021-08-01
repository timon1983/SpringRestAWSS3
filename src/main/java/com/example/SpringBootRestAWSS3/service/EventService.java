package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.model.Event;
import com.example.SpringBootRestAWSS3.model.File;
import com.example.SpringBootRestAWSS3.model.User;
import com.example.SpringBootRestAWSS3.repository.EventRepository;
import com.example.SpringBootRestAWSS3.repository.FileRepository;
import com.example.SpringBootRestAWSS3.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private EventRepository eventRepository;
    private UserAuthRepository userAuthRepository;
    public FileRepository fileRepository;

    @Autowired
    public EventService(EventRepository eventRepository, UserAuthRepository userAuthRepository, FileRepository fileRepository) {
        this.eventRepository = eventRepository;
        this.userAuthRepository = userAuthRepository;
        this.fileRepository = fileRepository;
    }

    //Create new event on DB
    public Event save(String fileName){
        return eventRepository.save(createEvent(fileName));
    }

    //Get all the events of a specific user
    public List<Event> findAllByUser(long userId){
        List<Event> events =  eventRepository.findAll();
        List<Event> eventsByUser =
                events.stream().filter(x->(x.getUser().getId() == userId)).collect(Collectors.toList());

        return eventsByUser;
    }

    //Create Event by fileName
    public Event createEvent(String fileName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        Optional<User> user = userAuthRepository.findByEmail(userEmail);
        User newUser = user.get();
        File file = new File();
        Event event = new Event();
        file.setFileName(fileName);
        event.setUser(newUser);
        event.setFile(file);
        return event;
    }
}
