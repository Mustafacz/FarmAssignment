package org.example;

public class Animal extends Entity {
    private final String species;
    private static final int lastAnimalId = 0;


    public Animal(int id, String species) {
        super(id, "Animal");
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }


    public void feed(Crop crop) {
        System.out.println(species + " is eating " + crop.getCropType());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Species: " + species;
    }
}



