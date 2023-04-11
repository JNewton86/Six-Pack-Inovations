package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.models.beerenums.PackagingType;

@JsonDeserialize(builder = UpdatePlaylistRequest.Builder.class)
public class UpdateInventoryRequest {
    private final String beerId;
    private final PackagingType packagingType;
    private Integer availableUnits;
    private Integer reservedUnits;

    private UpdateInventoryRequest(String beerId, PackagingType packagingType, Integer availableUnits, Integer reservedUnits) {
        this.beerId = beerId;
        this.packagingType = packagingType;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
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


    @Override
    public String toString() {
        return "UpdateInventoryRequest{" +
                "beerId='" + beerId + '\'' +
                ", packagingType='" + packagingType + '\'' +
                ", availableUnits='" + availableUnits + '\'' +
                ", reservedUnits='" + reservedUnits + '\'' +
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
        public UpdateInventoryRequest build() {
            return new UpdateInventoryRequest(beerId, packagingType, availableUnits, reservedUnits);
        }
    }
}
