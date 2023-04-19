package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.math.BigDecimal;

public class OrderItem {
    private String id;
    private int units;
    private BigDecimal lineItemPrice;

    public String getId() {
        return id;
    }

    //TODO: I THINK WE SHOULD MAYBE FIGURE OUT IF WE WANT TO USE THE UTILS TO MAKE THIS? WOULD THAT BE HERE OR IN THE MODEL?
    //DO WE NEED A MODEL?
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
