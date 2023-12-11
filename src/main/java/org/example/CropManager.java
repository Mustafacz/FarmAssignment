package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CropManager {
    private List<Crop> crops;
    private Scanner scanner;

    public CropManager() {
        crops = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    private int readIntInput() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    private void showCropIds() {
        System.out.println("Existing IDs of different crops:");
        for (Crop crop : crops) {
            System.out.println("Crop ID: " + crop.getId());
        }
    }

    public void showAvailableIds() {
        showCropIds();
        }


    public Crop findCropById(int cropId) {
        for (Crop crop : crops) {
            if (crop.getId() == cropId) {
                return crop;
            }
        }
        return null;
    }

    public void viewCrops() {
        if (crops.isEmpty()) {
            System.out.println("No crops are available at this moment. ");
        } else {
            System.out.println("List of Crops: ");
            for (Crop crop : crops) {
                System.out.println(crop.getDescription());
            }
        }
    }

    public void addOrUpdateCrop() {
        showAvailableIds();

        System.out.println("Enter the ID for the crop: ");

        int cropId = readIntInput();
        scanner.nextLine();

        Crop existingCrop = findCropById(cropId);

        if (existingCrop != null) {
            System.out.println("Enter the quantity you wish to add: ");
            int quantityToAdd = readIntInput();
            existingCrop.increaseQuantity(quantityToAdd);
            System.out.println("The quantity has been updated for the chosen crop!");
        } else {
            System.out.println("Enter crop type: ");
            String cropType = scanner.nextLine();

            System.out.println("Enter quantity for the new crop: ");
            int newQuantity = readIntInput();
            scanner.nextLine();

            Crop newCrop = new Crop(cropId, cropType, newQuantity);
            crops.add(newCrop);
            System.out.println("New crop has been added.");
        }
    }

    public void removeCrop() {
        showAvailableIds();

        System.out.println("Write the ID for the crop you want to delete: ");
        int cropId = readIntInput();
        scanner.nextLine();

        boolean removed = crops.removeIf(crop -> crop.getId() == cropId);

        if (removed) {
            System.out.println("Crop has been removed.");
        } else {
            System.out.println("No crop found with the specified ID.");
        }
    }

    public List<Crop> getCrops() {
        return crops;
    }

}
