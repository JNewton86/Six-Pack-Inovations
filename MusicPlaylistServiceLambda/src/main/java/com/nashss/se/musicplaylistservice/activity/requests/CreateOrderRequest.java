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
    private List<OrderItem> orderItems;
    private Double totalCost;
    private boolean orderProcessed;
    private String clientId;

    public CreateOrderRequest(String orderId, List<OrderItem> orderItems, Double totalCost, boolean orderProcessed, String clientId) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
        this.orderProcessed = orderProcessed;
        this.clientId = clientId;
    }

    public String clientId() {
        return clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Double getTotalCost() {
        return totalCost;
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
        private List<OrderItem> orderItems;
        private Double totalCost;
        private boolean orderProcessed;
        private String clientId;

        public CreateOrderRequest.Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public CreateOrderRequest.Builder withOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
            return this;
        }

        public CreateOrderRequest.Builder withTotalCost(Double totalCost) {
            this.totalCost = totalCost;
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
            return new CreateOrderRequest(orderId, orderItems, totalCost, orderProcessed, clientId);
        }
    }
}
