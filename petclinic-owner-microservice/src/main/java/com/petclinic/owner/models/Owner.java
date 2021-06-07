package com.petclinic.owner.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a pet owner.
 */
@Entity(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String phoneNumber;

    @ElementCollection
    private List<Long> petIds = new ArrayList<>();

    protected Owner() {

    }

    public Owner(String name, String address, String city, String phoneNumber) {

        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;

    }

    // Builder pattern using static builder
    public static OwnerBuilder builder() {
        return new OwnerBuilder();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // This is needed to update the relationship between Pet and Owner when adding a Pet
    public void addPet(Long petId) {
        petIds.add(petId);
    }

    // This is needed to update the relationship between Pet and Owner when removing a Pet
    public void removePet(Long petId) {
        petIds.remove(petId);
    }

    public List<Long> getPetIds() {
        return this.petIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Owner {");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');

        return sb.toString();
    }

    public static final class OwnerBuilder {

        private Owner owner;

        private OwnerBuilder() {
            owner = new Owner();
        }

        public OwnerBuilder withId(Long id) {
            owner.setId(id);
            return this;
        }

        public OwnerBuilder withName(String name) {
            owner.setName(name);
            return this;
        }

        public OwnerBuilder withAddress(String address) {
            owner.setAddress(address);
            return this;
        }

        public OwnerBuilder withCity(String city) {
            owner.setCity(city);
            return this;
        }

        public OwnerBuilder withPhoneNumber(String phoneNumber) {
            owner.setPhoneNumber(phoneNumber);
            return this;
        }

        public OwnerBuilder withPet(Long petId) {
            owner.addPet(petId);
            return this;
        }

        public Owner build() {
            return owner;
        }
    }
}
