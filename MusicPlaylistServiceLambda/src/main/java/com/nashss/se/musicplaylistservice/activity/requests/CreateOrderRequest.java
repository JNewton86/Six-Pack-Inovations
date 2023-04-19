package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import static com.nashss.se.musicplaylistservice.utils.CollectionUtils.copyToList;

@JsonDeserialize(builder = CreateOrderRequest.Builder.class)
public class CreateOrderRequest {
    private String orderId;
    private String clientId;
    private List<OrderItem> orderItems;
    private Double totalCost;
    private boolean orderProcessed;

    public CreateOrderRequest(String orderId, String clientId, List<OrderItem> orderItems, Double totalCost, boolean orderProcessed) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
        this.orderProcessed = orderProcessed;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public List<OrderItem> getOrderItems() {
        return copyToList(orderItems);
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public boolean isOrderProcessed() {
        return orderProcessed;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "orderId='" + orderId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", orderItems=" + orderItems +
                ", totalCost=" + totalCost +
                ", orderProcessed=" + orderProcessed +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String orderId;
        private String clientId;
        private List<OrderItem> orderItems;
        private Double totalCost;
        private boolean orderProcessed;

        public CreateOrderRequest.Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public CreateOrderRequest.Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public CreateOrderRequest.Builder withOrderItems(List<OrderItem> orderItems) {
            this.orderItems = copyToList(orderItems);
            return this;
        }
        //TODO: LOOK AT THIS TOTAL COST MAKE SURE IT IS A BIGDECIMAL
        public CreateOrderRequest.Builder withTotalCost(Double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public CreateOrderRequest.Builder withOrderProcessed(boolean orderProcessed) {
            this.orderProcessed = orderProcessed;
            return this;
        }

        public CreateOrderRequest build() {
            return new CreateOrderRequest(orderId, clientId, orderItems, totalCost, orderProcessed);
        }
    }
}
