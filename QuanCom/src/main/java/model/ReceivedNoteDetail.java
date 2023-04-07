package model;

public class ReceivedNoteDetail {
    private int materialId;
    public int getMaterialId() {
        return materialId;
    }
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    private int receivedNoteId;

    public int getReceivedNoteId() {
        return receivedNoteId;
    }

    public void setReceivedNoteId(int receivedNoteId) {
        this.receivedNoteId = receivedNoteId;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
