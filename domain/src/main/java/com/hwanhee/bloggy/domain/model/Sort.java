package com.hwanhee.bloggy.domain.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Sort {
    private enum Order {
        LATEST,
        ACCURACY;

        private String toNaverString() {
            return switch (this) {
                case LATEST -> "date";
                case ACCURACY -> "sim";
                default -> throw new RuntimeException("Unknown order");
            };
        }

        private String toKakaoString() {
            return switch (this) {
                case LATEST -> "recency";
                case ACCURACY -> "accuracy";
                default -> throw new RuntimeException("Unknown order");
            };
        }
    }


    Order order;

    public String toNaverString() {
        return order.toNaverString();
    }

    public String toKakaoString() {
        return order.toKakaoString();
    }

    private Sort(Order order) {
        this.order = order;
    }

    public static Sort from(Order order) {
        return new Sort(order);
    }

    public static Sort from(String sortString) {
        return switch (sortString.toLowerCase()) {
            case "latest", "date" -> new Sort(Order.LATEST);
            case "accuracy", "sim" -> new Sort(Order.ACCURACY);
            default -> throw new IllegalArgumentException("Invalid sort string: " + sortString);
        };
    }

    public Order getOrder() {
        return order;
    }

    public static Sort ACCURACY = Sort.from(Order.ACCURACY);
    public static Sort LATEST = Sort.from(Order.LATEST);
}
