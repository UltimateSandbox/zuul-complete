package com.petclinic.owner.repositories;


import com.petclinic.owner.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    List<Owner> findOwnerByName(String name);
}
