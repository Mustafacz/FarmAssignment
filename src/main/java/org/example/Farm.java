package org.example;


import java.io.*;
import java.util.Scanner;


public class Farm {
    private final AnimalManager animalManager;
    private final CropManager cropManager;
    private final Scanner scanner;

    public Farm() {
        cropManager = new CropManager();
        animalManager = new AnimalManager(cropManager);
        scanner = new Scanner(System.in);
    }


    public void mainMenu() {
        int userChoice;

        do {
            System.out.println("1. View Crops");
            System.out.println("2. Add or Update Crop");
            System.out.println("3. Remove Crop");
            System.out.println("4. View Animals");
            System.out.println("5. Add or Update Animal");
            System.out.println("6. Remove Animal");
            System.out.println("7. Feed Animal");
            System.out.println("8. Save");
            System.out.println("0. Exit");

            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    cropManager.viewCrops();
                    break;
                case 2:
                    cropManager.addOrUpdateCrop();
                    break;
                case 3:
                    cropManager.removeCrop();
                    break;
                case 4:
                    animalManager.viewAnimals();
                    break;
                case 5:
                    animalManager.addOrUpdateAnimal();
                    break;
                case 6:
                    animalManager.removeAnimal();
                    break;
                case 7:
                    animalManager.feedAnimal();
                    break;
                case 8:
                    saveAndLoadData();
                    break;
                case 0:
                    System.out.println("Exiting program! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        } while (userChoice != 0);
    }
    public void saveAndLoadData() {
        saveFarmData();
        loadFarmData();
    }

    private void saveFarmData() {
        try (PrintWriter writer = new PrintWriter("file_data.txt")) {
            for (Animal animal : animalManager.getAnimals()) {
                writer.write("Animal," + animal.getId() + "," + animal.getSpecies() + "\n");
            }
            for (Crop crop : cropManager.getCrops()) {
                writer.write("Crop," + crop.getId() + "," + crop.getCropType() + "," + crop.getQuantity() + "\n");
            }
            System.out.println("Farm data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving farm data: " + e.getMessage());
        }
    }

    private void loadFarmData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("file_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    int id = Integer.parseInt(parts[1]);
                    if ("Animal".equals(parts[0])) {
                        String species = parts[2];
                        animalManager.getAnimals().add(new Animal(id, species));
                    } else if ("Crop".equals(parts[0])) {
                        String cropType = parts[2];
                        int quantity = Integer.parseInt(parts[3]);
                        cropManager.getCrops().add(new Crop(id, cropType, quantity));
                    }
                }
            }
            System.out.println("Farm data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading farm data: " + e.getMessage());
        }
    }
}



