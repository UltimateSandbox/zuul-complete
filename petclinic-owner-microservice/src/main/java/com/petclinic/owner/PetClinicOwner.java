package com.petclinic.owner;

import com.petclinic.owner.controllers.OwnerController;
import com.petclinic.owner.models.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@EnableDiscoveryClient
@RibbonClient(name = "owner-service")
@SpringBootApplication
public class PetClinicOwner {

    private static ConfigurableApplicationContext context;
    private static OwnerController ownerController;

    public static void main(String[] args) {

        context = SpringApplication.run(PetClinicOwner.class, args);
        generateData();
    }

    private static void generateData() {

        ownerController = (OwnerController) context.getBean("ownerController");
        List<Owner> owners = new ArrayList<>();

        // Create Owners
        owners.add(Owner.builder().withName("Homer Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build());
        owners.add(Owner.builder().withName("Marge Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build());
        owners.add(Owner.builder().withName("Bart Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build());
        owners.add(Owner.builder().withName("Lisa Simpson").withAddress("742 Evergreen Terrace").withCity("Springfield").withPhoneNumber("9395550113").build());

        // Add Owners
        owners.forEach(owner -> {
            ownerController.add(owner);
            System.out.println("Adding: " + owner.toString());
        });

        List<Owner> installedOwners = ownerController.getAll();

        installedOwners.forEach(owner -> {
            System.out.println("Successfully Added: " + owner.toString());
        });
    }

}
