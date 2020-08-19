package com.example.beautyshop;

public class Order {

    String CustomerName;
    String CustomerContact;
    String CustomerAddress;
    String Prodname;
    String ProdPrice;
    String ProdBrand;
    String modeofpayment;
    String Staus;

    public Order(String customerName, String customerContact, String customerAddress, String prodname, String prodPrice, String prodBrand, String modeofpayment, String staus) {
        CustomerName = customerName;
        CustomerContact = customerContact;
        CustomerAddress = customerAddress;
        Prodname = prodname;
        ProdPrice = prodPrice;
        ProdBrand = prodBrand;
        this.modeofpayment = modeofpayment;
        Staus = staus;
    }

    public Order() {

    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerContact() {
        return CustomerContact;
    }

    public void setCustomerContact(String customerContact) {
        CustomerContact = customerContact;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getProdname() {
        return Prodname;
    }

    public void setProdname(String prodname) {
        Prodname = prodname;
    }

    public String getProdPrice() {
        return ProdPrice;
    }

    public void setProdPrice(String prodPrice) {
        ProdPrice = prodPrice;
    }

    public String getProdBrand() {
        return ProdBrand;
    }

    public void setProdBrand(String prodBrand) {
        ProdBrand = prodBrand;
    }

    public String getModeofpayment() {
        return modeofpayment;
    }

    public void setModeofpayment(String modeofpayment) {
        this.modeofpayment = modeofpayment;
    }

    public String getStaus() {
        return Staus;
    }

    public void setStaus(String staus) {
        Staus = staus;
    }
}
