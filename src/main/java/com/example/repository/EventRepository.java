package com.example.repository;

import com.example.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Hao on 11/11/2016.
 */
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByName(@Param("name") String name);
    List<Event> findByNumberLimit(@Param("numberLimit") Integer numberLimit);
}
