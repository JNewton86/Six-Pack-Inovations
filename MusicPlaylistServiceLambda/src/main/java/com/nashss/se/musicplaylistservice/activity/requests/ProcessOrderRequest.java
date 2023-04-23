package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.OrderItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@JsonDeserialize(builder = ProcessOrderRequest.Builder.class)
public class ProcessOrderRequest {
    private String orderId;
    private boolean orderProcessed;

    private final Logger log = LogManager.getLogger();

    private ProcessOrderRequest(String orderId, boolean orderProcessed) {
        this.orderId = orderId;
        this.orderProcessed = orderProcessed;
    }

    public String getOrderId(){
        return orderId;
    }

    public boolean isOrderProcessed(){
        return orderProcessed;
    }

    @Override
    public String toString() {
        return "ProcessOrderRequest{" +
                "orderId='" + this.orderId + '\'' +
                ", orderProcessed='" + this.orderProcessed + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String orderId;
        private boolean orderProcessed;

        public Builder withOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withOrderProcessed(boolean orderProcessed) {
            this.orderProcessed = orderProcessed;
            return this;
        }

        public ProcessOrderRequest build() {
            return new ProcessOrderRequest(orderId, orderProcessed);
        }
    }
}