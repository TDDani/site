package com.example.xowrld.Repository;

import com.example.xowrld.Model.UpcomingEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpcomingEventRepo extends CrudRepository<UpcomingEvent, Long> {

    List<UpcomingEvent> findAllByOrderByTimeDesc();
    List<UpcomingEvent> findAllByOrderByTimeAsc();


}
