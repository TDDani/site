package com.example.xowrld.Repository;

import com.example.xowrld.Model.Beat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeatRepository extends CrudRepository<Beat, Long> {

    Optional<Beat> getById(Long id);

    Optional<Beat> findBeatByTitle(String title);


}
