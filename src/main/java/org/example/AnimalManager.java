package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AnimalManager {
    private List<Animal> animals;
    private final CropManager cropManager;
    private final Scanner scanner;

    public AnimalManager(CropManager cropManager) {
        animals = new ArrayList<>();
        this.cropManager = cropManager;
        scanner = new Scanner(System.in);
    }

    private int readIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }


    private void showAnimalIds() {
        System.out.println("Existing IDs of different animals:");
        for (Animal animal : animals) {
            System.out.println("Animal ID: " + animal.getId());
        }
    }
    public void showAvailableIds() {
        showAnimalIds();
        }


    public Animal findAnimalById(int animalId) {
        for (Animal animal : animals) {
            if (animal.getId() == animalId) {
                return animal;
            }
        }
        return null;
    }

    public void viewAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No Animals are available at this moment. ");
        } else {
            System.out.println("List of Animals: ");
            for (Animal animal : animals) {
                System.out.println(animal.getDescription());
            }
        }
    }

    public void addOrUpdateAnimal() {
        showAvailableIds();

        System.out.println("Enter the ID for the animal: ");
        int animalId = readIntInput();
        scanner.nextLine();

        Animal existingAnimal = findAnimalById(animalId);

        String newSpecies = null;
        if (existingAnimal != null) {
            System.out.println("There is already an animal with this ID. ");
        } else {
            System.out.println("Write the species for the new animal: ");
            String species = scanner.nextLine();

            Animal newAnimal = new Animal(animalId, species);
            animals.add(newAnimal);
            System.out.println("New animal has been added.");
        }
    }

    public void removeAnimal() {
        showAvailableIds();

        System.out.println("Write the ID for the animal you want to delete: ");
        int animalId = readIntInput();
        scanner.nextLine();

        boolean removed = animals.removeIf(animal -> animal.getId() == animalId);

        if (removed) {
            System.out.println("Animal has been removed.");
        } else {
            System.out.println("No animal found with the specified ID.");
        }
    }

    public void feedAnimal() {
        cropManager.showAvailableIds();

        System.out.println("Enter the ID of the crop you wish to feed with: ");
        int cropId = readIntInput();
        scanner.nextLine();

        Crop selectedCrop = cropManager.findCropById(cropId);

        if (selectedCrop == null) {
            System.out.println("No crop found with the specified ID.");
            return;
        }

        showAvailableIds();

        System.out.println("Enter the ID for the animal to feed: ");
        int animalId = readIntInput();
        scanner.nextLine();

        Animal selectedAnimal = findAnimalById(animalId);

        if (selectedAnimal == null) {
            System.out.println("No animal found with the specified ID.");
            return;
        }

        selectedAnimal.feed(selectedCrop);

        System.out.println("Animal has been fed with the selected crop.");

        selectedCrop.decreaseQuantity();

        if (selectedCrop.getQuantity() == 0) {
            System.out.println("There are no more crops left to feed.");
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }


}
