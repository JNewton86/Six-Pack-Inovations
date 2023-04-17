package com.nashss.se.musicplaylistservice.models;

import java.math.BigDecimal;
import java.util.Objects;


public class BeerModel {
    private String beerId;
    private String beerType;
    private String name;
    private String packagingType;
    private Double unitPrice;
    private Integer availableUnits;
    private Integer reservedUnits;

    public BeerModel(String id, String beerType, String name, String packagingType,
                     Double unitPrice, Integer availableUnits, Integer reservedUnits) {
        this.beerId = id;
        this.beerType = beerType;
        this.name = name;
        this.packagingType = packagingType;
        this.unitPrice = unitPrice;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
    }
    public String getBeerId() {
        return beerId;
    }

   public String getPackagingType() {
        return packagingType;
    }

    public String getBeerType() {
        return beerType;
    }

    public String getName() {
        return name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getAvailableUnits() {
        return availableUnits;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerModel beer = (BeerModel) o;
        return Objects.equals(beerId, beer.beerId) && Objects.equals(beerType, beer.beerType)
                && Objects.equals(name, beer.name) && Objects.equals(packagingType, beer.packagingType) && Objects.equals(unitPrice, beer.unitPrice)
                && Objects.equals(availableUnits, beer.availableUnits) && Objects.equals(reservedUnits, beer.reservedUnits);
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
        private String beerType;
        private String name;
        private String packagingType;
        private Double unitPrice;
        private Integer availableUnits;
        private Integer reservedUnits;

        public BeerModel.Builder withBeerId(String beerId) {
            this.beerId = beerId;
            return this;
        }

        //how to do this with ENUMS
        public BeerModel.Builder withBeerType(String beerType) {
            this.beerType = beerType;
            return this;
        }

        public BeerModel.Builder withName(String name) {
            this.name = name;
            return this;
        }

        //how to do this with an Enum?
        public BeerModel.Builder withPackagingType(String packagingType) {
            this.packagingType = packagingType;
            return this;
        }

        public BeerModel.Builder withUnitPrice(Double unitPrice) {
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
