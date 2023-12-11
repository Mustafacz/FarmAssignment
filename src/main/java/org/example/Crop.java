package org.example;

public class Crop extends Entity {
    private final String cropType;
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
}



