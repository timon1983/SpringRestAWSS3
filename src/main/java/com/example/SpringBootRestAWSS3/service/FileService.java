package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.model.File;
import com.example.SpringBootRestAWSS3.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void delete(String fileName){
        Optional<File> file = fileRepository.findByFileName(fileName);
        File getFile = file.get();
        fileRepository.deleteById(getFile.getId());
    }
}
