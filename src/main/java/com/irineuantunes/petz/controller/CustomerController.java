package com.irineuantunes.petz.controller;

import com.irineuantunes.petz.dao.CustomerService;
import com.irineuantunes.petz.dao.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<CustomerEntity> list() {
        return customerService.list();
    }

    @PostMapping("/")
    public CustomerEntity add(@RequestBody CustomerEntity customer) {
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> get(@PathVariable Long id) {
        CustomerEntity c = customerService.findById(id);
        if(c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(c);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(@PathVariable Long id, @RequestBody CustomerEntity customer) {
        CustomerEntity c = customerService.findById(id);
        if(c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());

        return ResponseEntity.ok(customerService.save(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        CustomerEntity c = customerService.findById(id);
        if(c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        customerService.deleteById(c.getId());
        return ResponseEntity.ok("done");
    }
}
