package com.example.xowrld.Repository;

import com.example.xowrld.Model.SoldBeat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoldBeatRepository extends CrudRepository<SoldBeat, Long> {
}
