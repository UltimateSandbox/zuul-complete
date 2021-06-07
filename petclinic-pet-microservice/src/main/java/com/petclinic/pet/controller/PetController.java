package com.petclinic.pet.controller;


import com.petclinic.pet.models.Pet;
import com.petclinic.pet.models.PetWithOwner;
import com.petclinic.pet.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pet")
public class PetController implements BasicController<Pet> {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Override
    @PostMapping(value = "addPet", produces = "application/json")
    public Pet add(@RequestBody Pet pet) {

        return this.petService.add(pet);
    }

    @Override
    @GetMapping(value = "getPetById/{id}", produces = "application/json")
    public PetWithOwner get(@PathVariable("id") Long id) {
        PetWithOwner petWithOwner = new PetWithOwner();
        Pet pet = this.petService.get(id);
        petWithOwner.setPet(pet);
        return petWithOwner;
    }

    @Override
    @PutMapping(value = "updatePet", produces = "application/json")
    public Pet modify(@RequestBody Pet pet) {

        return this.petService.modify(pet);
    }

    @Override
    @DeleteMapping(value = "deletePet/{id}", produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        this.petService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "getAllPets", produces = "application/json")
    public List<Pet> getAll() {

        return this.petService.getAll();

    }

    @GetMapping(value = "getPetByName/{name}", produces = "application/json")
    public List<Pet> getPetByName(@PathVariable String name) {

        return this.petService.getPetByName(name);
    }
}
