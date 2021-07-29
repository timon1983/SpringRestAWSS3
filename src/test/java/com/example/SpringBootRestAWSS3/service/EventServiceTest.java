package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.model.Event;
import com.example.SpringBootRestAWSS3.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;



class EventServiceTest {

    @Mock
    private EventRepository eventRepository = Mockito.mock(EventRepository.class);
    @Mock
    private EventService eventService = Mockito.mock(EventService.class);

    @Test
    void checkSaveService_Should_Return_Event() {
        Event event = new Event();
        when(eventRepository.save(event)).thenReturn(event);
    }
    @Test
    void checkGetAllService_Should_Show_All_of_Events() {
        List<Event> eventList = new ArrayList<>();
        when(eventRepository.findAll()).thenReturn(eventList);
    }

}