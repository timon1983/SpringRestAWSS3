package com.example.SpringBootRestAWSS3.service;

import com.example.SpringBootRestAWSS3.repository.S3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3Service {

    private S3Repository s3Repository;
    private EventService eventService;
    private FileService fileService;

    @Autowired
    public S3Service(S3Repository s3Repository, EventService eventService, FileService fileService) {
        this.s3Repository = s3Repository;
        this.eventService = eventService;
        this.fileService = fileService;
    }

    //Upload file to S3
    public String uploadFile(MultipartFile multipartFile){
        File convFile = convertMultiPartToFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename().replace(" ", "_");

        if(s3Repository.listFiles(fileName)){//if such fileName is not to S3 then write him on DB
             s3Repository.upload(convFile,fileName);
             eventService.save(fileName);
             convFile.delete();
             return fileName + " is uploaded!";
         }
         return "The file with such " + fileName + " already exist!";
    }

    //Delete file from S3
    public String deleteFile(String fileUrl){
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if(!s3Repository.listFiles(fileName)) {   //if such fileName is not to S3 then write him on DB
                s3Repository.delete(fileName);
                fileService.delete(fileName);
                return fileName + " is deleted!";
            }
            return fileName + " is not exist!";
    }

    //Download file from S3
    public String getFile(String fileName){
        if(!s3Repository.listFiles(fileName)){
                s3Repository.getByName(fileName);
                return "The file " + fileName + " received!";
            }
            return fileName + " is not exist!";
    }


    //Convert multipart file to usually file
    private File convertMultiPartToFile(MultipartFile multipartFile) {
        File convFile = new File(multipartFile.getOriginalFilename());

        try(FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }
}
