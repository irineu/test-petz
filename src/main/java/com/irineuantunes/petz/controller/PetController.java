package com.irineuantunes.petz.controller;

import com.irineuantunes.petz.dao.CustomerService;
import com.irineuantunes.petz.dao.PetService;
import com.irineuantunes.petz.dao.entity.CustomerEntity;
import com.irineuantunes.petz.dao.entity.PetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping( "/customer/{idCustomer}/pet")
public class PetController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public ResponseEntity<Set<PetEntity>> list(@PathVariable Long idCustomer) {

        CustomerEntity owner = customerService.findById(idCustomer);
        if(owner == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(owner.getPets());
    }

    @PostMapping("/")
    public ResponseEntity<PetEntity> add(@PathVariable Long idCustomer, @RequestBody PetEntity pet) {

        CustomerEntity owner = customerService.findById(idCustomer);
        if(owner == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        pet.setOwner(customerService.findById(idCustomer));
        return ResponseEntity.ok(petService.save(pet));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetEntity> update(@PathVariable Long idCustomer, @PathVariable Long id, @RequestBody PetEntity petChanged) {

        CustomerEntity owner = customerService.findById(idCustomer);
        if(owner == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        PetEntity pet = petService.findById(id);
        if(pet == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        pet.setName(petChanged.getName());
        return ResponseEntity.ok(petService.save(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long idCustomer, @PathVariable Long id) {

        CustomerEntity owner = customerService.findById(idCustomer);
        if(owner == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        PetEntity pet = petService.findById(id);
        if(pet == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        petService.deleteById(pet.getId());
        return ResponseEntity.ok("done");
    }
}
