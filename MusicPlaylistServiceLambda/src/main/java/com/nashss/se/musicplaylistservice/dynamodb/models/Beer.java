package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.math.BigDecimal;
import java.util.Objects;

@DynamoDBTable(tableName = "inventory")
public class Beer {
    public static final String BEERS_BY_TYPE_INDEX = "BeersByBeerTypeIndex";
    private String beerId;
    private String beerType;
    private String name;
    private String packagingType;
    private Double unitPrice;
    private Integer availableUnits;
    private Integer reservedUnits;

    @DynamoDBHashKey(attributeName = "beerId")
    public String getBeerId() {
        return beerId;
    }

    @DynamoDBRangeKey(attributeName = "packagingType")
    public String getPackagingType() {
        return packagingType;
    }

//    @DynamoDBTypeConverted(converter = BeerTypeConverter.class)
    @DynamoDBIndexHashKey(globalSecondaryIndexName = BEERS_BY_TYPE_INDEX, attributeName = "beerType")
    @DynamoDBAttribute(attributeName = "beerType")
    public String getBeerType() {
        return beerType;
    }

    @DynamoDBAttribute(attributeName = "beerName" )
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "unitPrice")
    public Double getUnitPrice() {
        return unitPrice;
    }

    @DynamoDBAttribute(attributeName = "availableUnits")
    public Integer getAvailableUnits() {
        return availableUnits;
    }

    @DynamoDBAttribute(attributeName = "reservedUnits")
    public Integer getReservedUnits() {
        return reservedUnits;
    }

    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAvailableUnits(Integer availableUnits) {
        this.availableUnits = availableUnits;
    }

    public void setReservedUnits(Integer reservedUnits) {
        this.reservedUnits = reservedUnits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Beer beer = (Beer) o;

        return Objects.equals(beerId, beer.beerId) && Objects.equals(beerType, beer.beerType) &&
                Objects.equals(name, beer.name) && Objects.equals(packagingType, beer.packagingType) &&
                Objects.equals(unitPrice, beer.unitPrice) && Objects.equals(availableUnits, beer.availableUnits) &&
                Objects.equals(reservedUnits, beer.reservedUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerId, beerType, name, packagingType, unitPrice, availableUnits, reservedUnits);
    }
}
