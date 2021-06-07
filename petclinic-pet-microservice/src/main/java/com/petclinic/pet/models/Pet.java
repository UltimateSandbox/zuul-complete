package com.petclinic.pet.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Date birthDate;
    private PetType petType;
    private Long ownerId;

    @ElementCollection
    private List<Long> visitIds = new ArrayList<>();

    protected Pet() {

    }

    public Pet(String name, Date birthDate, PetType petType, Long ownerId) {

        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.ownerId = ownerId;
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    // This is needed to update the relationship between Visit and Pet when adding a Visit
    public void addVisit(Long visitId) {
        visitIds.add(visitId);
    }

    // This is needed to update the relationship between Visit and Pet when removing a Visit
    public void removeVisit(Long visitId) {
        visitIds.remove(visitId);
    }

    // only include id field when generating equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pet {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", petType=").append(petType);
        sb.append(", ownerId=").append(ownerId);
        sb.append('}');
        return sb.toString();
    }

    public static final class PetBuilder {
        private Pet pet;

        private PetBuilder() {
            pet = new Pet();
        }

        public PetBuilder withId(Long id) {
            pet.setId(id);
            return this;
        }

        public PetBuilder withName(String name) {
            pet.setName(name);
            return this;
        }

        public PetBuilder withBirthDate(Date birthDate) {
            pet.setBirthDate(birthDate);
            return this;
        }

        public PetBuilder withPetType(PetType petType) {
            pet.setPetType(petType);
            return this;
        }

        public PetBuilder withOwnerId(Long ownerId) {
            pet.setOwnerId(ownerId);
            return this;
        }

        public PetBuilder withVisitId(Long visitId) {
            pet.addVisit(visitId);
            return this;
        }

        public Pet build() {
            return pet;
        }
    }
}
