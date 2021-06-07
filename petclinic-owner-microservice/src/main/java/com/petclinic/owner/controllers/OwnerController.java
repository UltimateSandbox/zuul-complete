package com.petclinic.owner.controllers;


import com.petclinic.owner.models.Owner;
import com.petclinic.owner.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("owner")
public class OwnerController implements BasicController<Owner> {

    private Logger logger = LoggerFactory.getLogger(OwnerController.class.getName());

    @Value("${message:Hello}")
    private String message;

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }

    @Override
    @PostMapping(value = "addOwner", produces = "application/json")
    public Owner add(@RequestBody Owner owner) {

        return this.ownerService.add(owner);
    }

    @Override
    @GetMapping(value = "getById/{id}", produces = "application/json")
    public Owner get(@PathVariable("id") Long id) {

        // Demonstrates exception handling with ResponseStatusException exception
        Owner owner = null;
        try {
            owner = this.ownerService.get(id);
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Owner [" + id + "] Not Found", exc);
        }
        logger.info("Owner " + id + " requested");
        return owner;
    }

    @Override
    @PutMapping(value = "updateOwner", produces = "application/json")
    public Owner modify(@RequestBody Owner owner) {

        return this.ownerService.modify(owner);
    }

    @Override
    @DeleteMapping(value = "deleteOwner/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        this.ownerService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "getAllOwners", produces = "application/json")
    public List<Owner> getAll() {

        List<Owner> all = this.ownerService.getAll();
        return all;
    }

    @GetMapping(value = "getOwnerByName/{name}", produces = "application/json")
    public List<Owner> getOwnerByName(@PathVariable("name") String name) {

        return this.ownerService.getOwnerByName(name);
    }

}
