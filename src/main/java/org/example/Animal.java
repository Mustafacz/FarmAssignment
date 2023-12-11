package org.example;

public class Animal extends Entity {
    private String species;
    private static int lastAnimalId = 0;


    public Animal(int id, String species) {
        super(id, "Animal");
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }
    public Animal(String species) {
        super("Animal");
        this.species = species;
    }

    public void feed(Crop crop) {
        System.out.println(species + " is eating " + crop.getCropType());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Species: " + species;
    }
}



