package com.petclinic.pet;

import com.petclinic.pet.controller.PetController;
import com.petclinic.pet.models.Pet;
import com.petclinic.pet.models.PetType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableDiscoveryClient
@RibbonClient(name = "pet-service")
@SpringBootApplication
public class PetClinicPet {


    private static ConfigurableApplicationContext context;
    private static PetController petController;

    public static void main(String[] args) {

        context = SpringApplication.run(PetClinicPet.class, args);
        generateData();
    }

    private static void generateData() {

        petController = (PetController) context.getBean("petController");
        List<Pet> petData = new ArrayList<>();

        // Pets for Homer
        petData.add(Pet.builder().withName("Strangles").withOwnerId(1L).withBirthDate(new Date()).withPetType(PetType.SNAKE).build());
        petData.add(Pet.builder().withName("Mojo").withOwnerId(1L).withBirthDate(new Date()).withPetType(PetType.MONKEY).build());
        petData.add(Pet.builder().withName("Pinchy").withOwnerId(1L).withBirthDate(new Date()).withPetType(PetType.LOBSTER).build());
        petData.add(Pet.builder().withName("Plopper").withOwnerId(1L).withBirthDate(new Date()).withPetType(PetType.PIG).build());

        // Pets for Marge
        petData.add(Pet.builder().withName("Greyhound").withOwnerId(2L).withBirthDate(new Date()).withPetType(PetType.DOG).build());

        // Pets for Bart
        petData.add(Pet.builder().withName("Laddie").withOwnerId(3L).withBirthDate(new Date()).withPetType(PetType.DOG).build());
        petData.add(Pet.builder().withName("Santa's Little Helper").withOwnerId(3L).withBirthDate(new Date()).withPetType(PetType.DOG).build());
        petData.add(Pet.builder().withName("Stampy").withOwnerId(3L).withBirthDate(new Date()).withPetType(PetType.ELEPHANT).build());
        petData.add(Pet.builder().withName("Duncan").withOwnerId(3L).withBirthDate(new Date()).withPetType(PetType.HORSE).build());


        // Pets for Lisa
        petData.add(Pet.builder().withName("Nibbles").withOwnerId(4L).withBirthDate(new Date()).withPetType(PetType.HAMPSTER).build());
        petData.add(Pet.builder().withName("Chirpy Boy").withOwnerId(4L).withBirthDate(new Date()).withPetType(PetType.LIZARD).build());
        petData.add(Pet.builder().withName("Bart Junior").withOwnerId(4L).withBirthDate(new Date()).withPetType(PetType.LIZARD).build());
        petData.add(Pet.builder().withName("Snowball IV").withOwnerId(4L).withBirthDate(new Date()).withPetType(PetType.CAT).build());
        petData.add(Pet.builder().withName("Princess").withOwnerId(4L).withBirthDate(new Date()).withPetType(PetType.HORSE).build());

        petData.forEach(pet -> {
            petController.add(pet);
            System.out.println("Adding Pet: " + pet.toString());
        });

        List<Pet> installedPets = petController.getAll();

        installedPets.forEach(pet -> {
            System.out.println("Successfully added: " + pet.toString());
        });

    }

}
