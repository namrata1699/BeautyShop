package com.example.beautyshop;

public class Admin_Product {

    private String name;
    private String price;
    private String  brand;
    private String category;
    private String mimgURL;

    public Admin_Product() {
    }

    public Admin_Product(String name, String price, String brand, String category, String mimgURL) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.mimgURL = mimgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMimgURL() {
        return mimgURL;
    }

    public void setMimgURL(String mimgURL) {
        this.mimgURL = mimgURL;
    }
}
