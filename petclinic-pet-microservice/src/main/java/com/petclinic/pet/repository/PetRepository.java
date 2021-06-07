package com.petclinic.pet.repository;


import com.petclinic.pet.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> getPetByName(String name);

}
