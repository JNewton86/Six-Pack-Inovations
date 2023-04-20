package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.nashss.se.musicplaylistservice.utils.ServiceUtilsSPI;

@DynamoDBDocument
public class OrderItem {
    private String id;
    private int units;
    private Double lineItemPrice;
    private String beerType;
    private String packagingType;
    private String name;

    public OrderItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (this.id == null) {
            this.id = ServiceUtilsSPI.generateUserId();
        }
    }

    public int getUnits() { return units; }

    public void setUnits(int units) {
        this.units = units;
    }

    public Double getLineItemPrice() {
        return lineItemPrice;
    }

    public void setLineItemPrice(Double lineItemPrice) {
        this.lineItemPrice = lineItemPrice;
    }

    public String getBeerType() { return beerType; }

    public void setBeerType(String beerType) { this.beerType = beerType; }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
