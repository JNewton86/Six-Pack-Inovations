package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.OrderModel;

public class GetOrderResult {
    private final OrderModel order;

    private GetOrderResult(OrderModel order) {
        this.order = order;
    }

    public OrderModel getPlaylist() {
        return order;
    }

    @Override
    public String toString() {
        return "GetPlaylistResult{" +
                "playlist=" + order +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private OrderModel order;

        public Builder withOrder(OrderModel order) {
            this.order = order;
            return this;
        }

        public GetOrderResult build() {
            return new GetOrderResult(order);
        }
    }
}