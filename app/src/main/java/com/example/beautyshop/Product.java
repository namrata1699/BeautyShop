package com.example.beautyshop;

public class Product {
    public String proname;
    public String proprice;
    public String procategory;
    public String probrand;

    public Product(String proname, String proprice, String procategory, String probrand) {
        this.proname = proname;
        this.proprice = proprice;
        this.procategory = procategory;
        this.probrand = probrand;
    }

    public Product() {
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getProprice() {
        return proprice;
    }

    public void setProprice(String proprice) {
        this.proprice = proprice;
    }

    public String getProcategory() {
        return procategory;
    }

    public void setProcategory(String procategory) {
        this.procategory = procategory;
    }

    public String getProbrand() {
        return probrand;
    }

    public void setProbrand(String probrand) {
        this.probrand = probrand;
    }
}
