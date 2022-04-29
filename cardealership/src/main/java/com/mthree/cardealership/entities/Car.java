
package com.mthree.cardealership.entities;

import java.math.BigDecimal;
import java.util.Objects;


public class Car {
    private int carId;
    private String make, model, color, interior, transmission, bodyStyle;
    private int year;
    private BigDecimal salePrice, msrp;
    private int mileage;
    private String vin;
    private boolean used;
    private boolean sold;

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    
    
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.carId;
        hash = 83 * hash + Objects.hashCode(this.make);
        hash = 83 * hash + Objects.hashCode(this.model);
        hash = 83 * hash + Objects.hashCode(this.color);
        hash = 83 * hash + this.year;
        hash = 83 * hash + Objects.hashCode(this.vin);
        hash = 83 * hash + (this.used ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.carId != other.carId) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (this.used != other.used) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return Objects.equals(this.vin, other.vin);
    }
    
    
}
