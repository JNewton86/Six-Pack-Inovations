package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;
import com.nashss.se.musicplaylistservice.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@JsonDeserialize(builder = CreateOrderRequest.Builder.class)
public class CreateOrderRequest {
    private String orderId;
    private String orderItems;
    private boolean orderProcessed;
    private String clientId;

    public CreateOrderRequest(String orderId, String orderItems, boolean orderProcessed, String clientId) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.orderProcessed = orderProcessed;
        this.clientId = clientId;
    }

    public String clientId() {
        return clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public boolean getOrderProcessed() {
        return orderProcessed;
    }

    public String getClientId() {
        return clientId;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String orderId;
        private String orderItems;    
        private boolean orderProcessed;
        private String clientId;

        public CreateOrderRequest.Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public CreateOrderRequest.Builder withOrderItems(String orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public CreateOrderRequest.Builder withOrderProcessed(boolean orderProcessed) {
            this.orderProcessed = orderProcessed;
            return this;
        }

        public CreateOrderRequest.Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public CreateOrderRequest build() {
            return new CreateOrderRequest(orderId, orderItems, orderProcessed, clientId);
        }
    }
}
