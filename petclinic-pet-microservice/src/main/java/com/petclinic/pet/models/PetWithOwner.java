package com.petclinic.pet.models;

public class PetWithOwner extends Pet {

    Owner owner;

    public PetWithOwner() {
    }

    public PetWithOwner(Owner owner) {

        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setPet(Pet pet) {
        this.setId(pet.getId());
        this.setBirthDate(pet.getBirthDate());
        this.setName(pet.getName());
        this.setPetType(pet.getPetType());
        this.setOwnerId(pet.getOwnerId());
    }
}
