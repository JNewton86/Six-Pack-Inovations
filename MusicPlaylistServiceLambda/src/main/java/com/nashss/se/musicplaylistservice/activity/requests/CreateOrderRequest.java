package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;


//Todo: THIS CLASS JUST NEEDS ALL OF THE ATTRIBUTES FROM THE ORDER CLASS. YOU CAN TAKE OUT The attributes name, quantity, packaging type etc and replace it with attribures in Order.java.
@JsonDeserialize(builder = CreateOrderRequest.Builder.class)
public class CreateOrderRequest {
    private String name;
    private Integer quantity;
    private String packagingType;

    public CreateOrderRequest(String name, Integer quantity, String packagingType) {
        this.name = name;
        this.quantity = quantity;
        this.packagingType = packagingType;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPackagingType() {
        return packagingType;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private Integer quantity;
        private String packagingType;

        public CreateOrderRequest.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateOrderRequest.Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public CreateOrderRequest.Builder withPackagingType(String packagingType) {
            this.packagingType = packagingType;
            return this;
        }

        public CreateOrderRequest build() {
            return new CreateOrderRequest(name, quantity, packagingType);
        }
    }
}
