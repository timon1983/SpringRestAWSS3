package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.model.User;
import com.example.SpringBootRestAWSS3.repository.UserAuthRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Mock
    private UserAuthRepository userAuthRepository = Mockito.mock(UserAuthRepository.class);
    @Mock
    private UserService userServiceMock = Mockito.mock(UserService.class);

    @Test
    void checkGetByIdService_Should_Return_User_By_Name() {
        User user = new User();
        when(userAuthRepository.findByName("name")).thenReturn(user);
    }
}