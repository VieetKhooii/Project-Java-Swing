package model;

public class Product {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private String unit;
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    private int amount;
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    private int price;
    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    private int categoryId;
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
