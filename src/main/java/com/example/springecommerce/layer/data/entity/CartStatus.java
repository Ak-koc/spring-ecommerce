package com.example.springecommerce.layer.data.entity;

public enum CartStatus {
    NEW("New"),
    COMPLETED("Complated");

    private final String label;

    CartStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static CartStatus getDefault() {
        return NEW;
    }
}

