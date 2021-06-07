package com.petclinic.owner.services;


import com.petclinic.owner.models.Owner;
import com.petclinic.owner.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService implements BasicService<Owner> {

    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {

        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner add(Owner owner) {

        return this.ownerRepository.save(owner);
    }

    @Override
    public Owner get(Long id) {

        Optional optional = this.ownerRepository.findById(id);

        Owner result = null;
        if (optional.isPresent()) {
            result = (Owner) optional.get();
        }
        return result;
    }

    @Override
    public Owner modify(Owner owner) {

        return this.ownerRepository.save(owner);
    }

    @Override
    public void delete(Long id) {

        Owner ownerToDelete = get(id);
        this.ownerRepository.delete(ownerToDelete);

    }

    @Override
    public List<Owner> getAll() {

        return (List<Owner>) ownerRepository.findAll();
    }


    public List<Owner> getOwnerByName(String name) {

        return this.ownerRepository.findOwnerByName(name);
    }
}
