package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.math.BigDecimal;


@JsonDeserialize(builder = UpdateInventoryRequest.Builder.class)
public class UpdateInventoryRequest {
    private final String beerId;
    private final String packagingType;
    private Integer availableUnits;
    private Integer reservedUnits;
    private Double unitPrice;

    private UpdateInventoryRequest(String beerId, String packagingType, Integer availableUnits,
                                   Integer reservedUnits, Double unitPrice) {
        this.beerId = beerId;
        this.packagingType = packagingType;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
        this.unitPrice = unitPrice;
    }

    public String getBeerId() {
        return beerId;
    }

    @JsonValue
    public String getPackagingType() {
        return packagingType;
    }

    public Integer getAvailableUnits() {
        return availableUnits;
    }

    public Integer getReservedUnits() {
        return reservedUnits;
    }

    public Double getUnitPrice() {
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
        private String packagingType;
        private Integer availableUnits;
        private Integer reservedUnits;
        private Double unitPrice;

        public Builder withBeerId(String beerId) {
            this.beerId = beerId;
            return this;
        }

        public Builder withPackagingType(String packagingType) {
            this.packagingType = packagingType;
            return this;
        }

        public Builder withAvailableUnits(Integer availableUnits) {
            this.availableUnits = availableUnits;
            return this;
        }
        public Builder withReservedUnits(Integer reservedUnits) {
            this.reservedUnits = reservedUnits;
            return this;
        }

        public Builder withUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }
        public UpdateInventoryRequest build() {
            return new UpdateInventoryRequest(beerId, packagingType, availableUnits, reservedUnits, unitPrice);
        }
    }
}
