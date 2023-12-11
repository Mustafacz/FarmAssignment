package org.example;

public class Entity {
    protected static int lastAnimalId = 0;
    protected static int lastCropId = 0;
    public int id;
    protected String type;
    protected String name;

    public Entity(int id, String type) {
        this.type = type;
        this.id = generateId(type);
    }

    public Entity(String animal) {
    }

    private int generateId(String type) {
        if ("Animal".equals(type)) {
            return ++lastAnimalId;
        } else if ("Crop".equals(type)) {
            return ++lastCropId;
        }
        return -1;
    }

    public int getId() {
        return id;
    }


    public String getDescription() {
        return type + " ID: " + id;
    }
}