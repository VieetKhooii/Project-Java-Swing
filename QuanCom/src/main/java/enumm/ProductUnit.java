package enumm;

public enum ProductUnit {
    DIA("dĩa"),
    TO("tô"),
    CHAI("chai"),
    LY("ly"),
    CHEN("chén"),
    LON("lon");

    private String unit;

    ProductUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}