package com.example.SpringBootRestAWSS3.repository;

import com.example.SpringBootRestAWSS3.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
