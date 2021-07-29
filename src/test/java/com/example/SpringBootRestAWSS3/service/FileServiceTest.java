package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class FileServiceTest {

    @Mock
    private FileRepository fileRepository = Mockito.mock(FileRepository.class);
    @Mock
    private FileService fileService = Mockito.mock(FileService.class);

    @Test
    void checkDeleteByIdService() {
        fileService.delete("top.txt");
        Mockito.verify(fileService).delete("top.txt");
    }
}