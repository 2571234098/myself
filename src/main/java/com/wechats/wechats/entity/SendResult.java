package com.wechats.wechats.entity;

public class SendResult {
    private Double x;

    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public SendResult(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
