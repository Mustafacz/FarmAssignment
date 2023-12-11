package org.example;

public class Crop extends Entity {
    private String cropType;
    private int quantity;


    public Crop(int id, String cropType, int quantity) {
        super(id,"Crop");
        this.cropType = cropType;
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantityToAdd) {
        this.quantity += quantityToAdd;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            this.quantity--;
        }
    }

    public String getCropType() {
        return cropType;
    }


    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Crop Type: " + cropType + ", Quantity: " + quantity;
    }

    public static Crop fromCsvString(String csvString) {
        String[] parts = csvString.split(",");
        if (parts.length == 4 && parts[0].equals("Crop")) {
            int id = Integer.parseInt(parts[1]);
            String cropType = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            return new Crop(id, cropType, quantity);
        }
        return null;
    }

    public String toCsvString() {
        return String.format("Crop,%d,%s,%d", getId(), cropType, quantity);
    }
}



