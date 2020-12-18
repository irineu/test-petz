package com.irineuantunes.petz.dao;

import com.irineuantunes.petz.dao.entity.CustomerEntity;
import com.irineuantunes.petz.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> list() {
        return customerRepository.findAll();
    }

    public CustomerEntity save (CustomerEntity c) {
        return customerRepository.save(c);
    }

    public CustomerEntity findById (Long id) {
        try{
            return customerRepository.findById(id).get();
        }catch(Exception e){
            return null;
        }

    }

    public void deleteById (Long id) {
        customerRepository.deleteById(id);
    }
}
