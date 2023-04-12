package com.nashss.se.musicplaylistservice.activity.requests;

import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;


@JsonDeserialize(builder = UpdateInventoryRequest.Builder.class)
public class UpdateInventoryRequest {
    private final String beerId;
    private final PackagingType packagingType;
    private Integer availableUnits;
    private Integer reservedUnits;
    private BigDecimal unitPrice;

    private UpdateInventoryRequest(String beerId, PackagingType packagingType, Integer availableUnits,
                                   Integer reservedUnits, BigDecimal unitPrice) {
        this.beerId = beerId;
        this.packagingType = packagingType;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
        this.unitPrice = unitPrice;
    }

    public String getbeerId() {
        return beerId;
    }

    public PackagingType getPackagingType() {
        return packagingType;
    }

    public Integer getavailableUnits() {
        return availableUnits;
    }

    public Integer getavreservedUnits() {
        return reservedUnits;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "UpdateInventoryRequest{" +
                "beerId='" + beerId + '\'' +
                ", packagingType='" + packagingType + '\'' +
                ", availableUnits='" + availableUnits + '\'' +
                ", reservedUnits='" + reservedUnits + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String beerId;
        private PackagingType packagingType;
        private Integer availableUnits;
        private Integer reservedUnits;
        private BigDecimal unitPrice;

        public Builder withId(String beerId) {
            this.beerId = beerId;
            return this;
        }

        public Builder withPackagingType(PackagingType packagingType) {
            this.packagingType = packagingType;
            return this;
        }

        public Builder withAvailableUnits(Integer availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }
        public Builder withReservedUnits(Integer reservedUnits) {
            this.reservedUnits = availableUnits;
            return this;
        }

        public Builder withUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }
        public UpdateInventoryRequest build() {
            return new UpdateInventoryRequest(beerId, packagingType, availableUnits, reservedUnits, unitPrice);
        }
    }
}
