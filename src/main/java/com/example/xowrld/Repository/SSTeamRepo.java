package com.example.xowrld.Repository;

import com.example.xowrld.Model.SSTeam;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SSTeamRepo extends CrudRepository<SSTeam, Long> {
}
