package com.nashss.se.musicplaylistservice.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Beer {
    private String id;
    private BeerType beerType;
    private String name;
    private PackagingType packagingType;
    private BigDecimal unitPrice;
    private Integer availableUnits;
    private Integer reservedUnits;

    public Beer(String id, BeerType beerType, String name, PackagingType packagingType, BigDecimal unitPrice, Integer availableUnits, Integer reservedUnits) {
        this.id = id;
        this.beerType = beerType;
        this.name = name;
        this.packagingType = packagingType;
        this.unitPrice = unitPrice;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
    }

    public String getId() {
        return id;
    }

    public BeerType getBeerType() {
        return beerType;
    }

    public String getName() {
        return name;
    }

    public PackagingType getPackagingType() {
        return packagingType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getAvailableUnits() {
        return availableUnits;
    }

    public Integer getReservedUnits() {
        return reservedUnits;
    }

    public void setId(String id) {
        this.id = id;
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
        Beer beer = (Beer) o;
        return Objects.equals(id, beer.id) && Objects.equals(beerType, beer.beerType) && Objects.equals(name, beer.name) && Objects.equals(packagingType, beer.packagingType) && Objects.equals(unitPrice, beer.unitPrice) && Objects.equals(availableUnits, beer.availableUnits) && Objects.equals(reservedUnits, beer.reservedUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beerType, name, packagingType, unitPrice, availableUnits, reservedUnits);
    }
}