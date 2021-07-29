package com.example.SpringBootRestAWSS3.rest;

import com.example.SpringBootRestAWSS3.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/s3")
public class S3Controller {

    private S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("uploadFile")
    @PreAuthorize("hasAuthority('file:upload')")
    public String uploadFile(@RequestPart("file") MultipartFile file){

        return s3Service.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    @PreAuthorize("hasAuthority('file:delete')")
    public String deleteFile(@RequestPart("url") String fileUrl) {

        return s3Service.deleteFile(fileUrl);
    }

    @GetMapping("downloadFile")
    @PreAuthorize("hasAuthority('file:download')")
    public String downloadFile(@RequestPart("fileName") String fileName ){

        return s3Service.getFile(fileName);
    }
}
