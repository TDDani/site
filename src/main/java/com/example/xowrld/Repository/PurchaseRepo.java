package com.example.xowrld.Repository;

import com.example.xowrld.Model.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseRepo extends CrudRepository<Purchase, Long> {

    List<Purchase> findAllByOrderByTime();
}
