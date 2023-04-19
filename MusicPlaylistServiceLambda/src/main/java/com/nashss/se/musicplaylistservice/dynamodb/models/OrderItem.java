package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.nashss.se.musicplaylistservice.utils.ServiceUtilsSPI;

import java.math.BigDecimal;

@DynamoDBDocument
public class OrderItem {
    private String id;
    private int units;
    private Double lineItemPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = ServiceUtilsSPI.generateUserId();
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Double getLineItemPrice() {
        return lineItemPrice;
    }

    public void setLineItemPrice(Double lineItemPrice) {
        this.lineItemPrice = lineItemPrice;
    }
}
