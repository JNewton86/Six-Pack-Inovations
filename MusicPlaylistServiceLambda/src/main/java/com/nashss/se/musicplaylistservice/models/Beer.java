package com.nashss.se.musicplaylistservice.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Beer {
    private String id;
    private Enum beerType;
    private String name;
    private Enum PackagingType;
    private BigDecimal unitPrice;
    private Integer availabeUnits;
    private Integer reservedUnits;

    public Beer(String id, Enum beerType, String name, Enum packagingType, BigDecimal unitPrice, Integer availabeUnits, Integer reservedUnits) {
        this.id = id;
        this.beerType = beerType;
        this.name = name;
        PackagingType = packagingType;
        this.unitPrice = unitPrice;
        this.availabeUnits = availabeUnits;
        this.reservedUnits = reservedUnits;
    }

    public String getId() {
        return id;
    }

    public Enum getBeerType() {
        return beerType;
    }

    public String getName() {
        return name;
    }

    public Enum getPackagingType() {
        return PackagingType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getAvailabeUnits() {
        return availabeUnits;
    }

    public Integer getReservedUnits() {
        return reservedUnits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBeerType(Enum beerType) {
        this.beerType = beerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackagingType(Enum packagingType) {
        PackagingType = packagingType;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAvailabeUnits(Integer availabeUnits) {
        this.availabeUnits = availabeUnits;
    }

    public void setReservedUnits(Integer reservedUnits) {
        this.reservedUnits = reservedUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Objects.equals(id, beer.id) && Objects.equals(beerType, beer.beerType) && Objects.equals(name, beer.name) && Objects.equals(PackagingType, beer.PackagingType) && Objects.equals(unitPrice, beer.unitPrice) && Objects.equals(availabeUnits, beer.availabeUnits) && Objects.equals(reservedUnits, beer.reservedUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beerType, name, PackagingType, unitPrice, availabeUnits, reservedUnits);
    }
}