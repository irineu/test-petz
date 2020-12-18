package com.irineuantunes.petz.dao;

import com.irineuantunes.petz.dao.entity.CustomerEntity;
import com.irineuantunes.petz.dao.entity.PetEntity;
import com.irineuantunes.petz.dao.repository.PetRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRespository petRespository;

    public List<PetEntity> list() {
        return petRespository.findAll();
    }

    public PetEntity save (PetEntity p) {
        return petRespository.save(p);
    }

    public PetEntity findById (Long id) {
        try{
            return petRespository.findById(id).get();
        }catch(Exception e) {
            return null;
        }
    }

    public void deleteById(Long id) {
        petRespository.deleteById(id);
    }
}
