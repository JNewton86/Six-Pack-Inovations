package com.nashss.se.musicplaylistservice.activity.requests;

public class GetOrderRequest {
    private final String orderId;

    private GetOrderRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "GetPlaylistRequest{" +
                "id='" + orderId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orderId;

        public Builder withId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public GetOrderRequest build() {
            return new GetOrderRequest(orderId);
        }
    }
}