package com.irineuantunes.petz.dao.repository;

import com.irineuantunes.petz.dao.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRespository extends JpaRepository<PetEntity, Long> {

}
