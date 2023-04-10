package com.nashss.se.musicplaylistservice.models;

import java.math.BigDecimal;

public class OrderItem {

    private String id;
    private int units;
    private BigDecimal lineItemPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getLineItemPrice() {
        return lineItemPrice;
    }

    public void setLineItemPrice(BigDecimal lineItemPrice) {
        this.lineItemPrice = lineItemPrice;
    }
}
