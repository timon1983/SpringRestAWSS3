package com.example.SpringBootRestAWSS3.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class S3Repository {

    private AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public S3Repository(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void upload(File file, String fileName){

        s3client.putObject(bucketName,fileName,file);
    }

    public void delete(String fileName){

        s3client.deleteObject(bucketName,fileName);
    }

    public void getByName(String fileName){
        S3Object s3object = s3client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Checking fileName to not dublicate on S3
    public boolean listFiles(String fileName){
        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            if(os.getKey().equals(fileName)){
                return false;
            }
        }
        return true;
    }
}

