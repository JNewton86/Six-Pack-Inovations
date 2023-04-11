package com.nashss.se.musicplaylistservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.nashss.se.musicplaylistservice.models.beerenums.BeerType;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;

import java.math.BigDecimal;
import java.util.Objects;


@DynamoDBTable(tableName = "inventory")
public class BeerModel {
    private String beerId;
    private BeerType beerType;
    private String name;
    private PackagingType packagingType;
    private BigDecimal unitPrice;
    private Integer availableUnits;
    private Integer reservedUnits;

    public BeerModel(String id, BeerType beerType, String name, PackagingType packagingType,
                     BigDecimal unitPrice, Integer availableUnits, Integer reservedUnits) {
        this.beerId = id;
        this.beerType = beerType;
        this.name = name;
        this.packagingType = packagingType;
        this.unitPrice = unitPrice;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
    }
    @DynamoDBHashKey(attributeName = "id")
    public String getBeerId() {
        return beerId;
    }

    @DynamoDBRangeKey(attributeName = "packagingType")
    public PackagingType getPackagingType() {
        return packagingType;
    }

    @DynamoDBAttribute(attributeName = "beerType")
    public BeerType getBeerType() {
        return beerType;
    }

    @DynamoDBAttribute(attributeName = "beerName")
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "unitPrice")
    public BigDecimal getUnitPrice() {
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

    public void setBeerType(BeerType beerType) {
        this.beerType = beerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackagingType(PackagingType packagingType) {
        this.packagingType = packagingType;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerModel beer = (BeerModel) o;
        return Objects.equals(beerId, beer.beerId) && Objects.equals(beerType, beer.beerType) && Objects.equals(name, beer.name) && Objects.equals(packagingType, beer.packagingType) && Objects.equals(unitPrice, beer.unitPrice) && Objects.equals(availableUnits, beer.availableUnits) && Objects.equals(reservedUnits, beer.reservedUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerId, beerType, name, packagingType, unitPrice, availableUnits, reservedUnits);
    }
    //CHECKSTYLE:OFF:Builder
    public static BeerModel.Builder builder() {
        return new BeerModel.Builder();
    }

    public static class Builder {
        private String beerId;
        private BeerType beerType;
        private String name;
        private PackagingType packagingType;
        private BigDecimal unitPrice;
        private Integer availableUnits;
        private Integer reservedUnits;

        public BeerModel.Builder withBeerId(String beerId) {
            this.beerId = beerId;
            return this;
        }

        //how to do this with ENUMS
        public BeerModel.Builder withBeerType(BeerType beerType) {
            this.beerType = beerType;
            return this;
        }

        public BeerModel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        //how to do this with an Enum?
        public BeerModel.Builder withPackagingType(PackagingType packagingType) {
            this.packagingType = packagingType;
            return this;
        }

        public BeerModel.Builder withUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public BeerModel.Builder withAvailableUnits(Integer availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }

        public BeerModel.Builder withReservedUnits(Integer reservedUnits) {
            this.reservedUnits = reservedUnits;
            return this;
        }

        public BeerModel build() {
            return new BeerModel(beerId, beerType, name, packagingType, unitPrice, availableUnits, reservedUnits);
        }
    }

}
