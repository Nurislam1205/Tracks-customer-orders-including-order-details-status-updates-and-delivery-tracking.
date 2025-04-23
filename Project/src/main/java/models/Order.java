package models;

import managers.OrderManager.DeliveryType;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int cnt = 1;
    private int id;
    private String nameOrder;
    private float weight;
    private DeliveryType deliveryType;
    private float totalPrice;
    private String username;

    public Order(String nameOrder, float weight, DeliveryType deliveryType, String username) {
        this.id = cnt++;
        this.nameOrder = nameOrder;
        this.weight = weight;
        this.deliveryType = deliveryType;
        this.totalPrice = weight * deliveryType.getCostPerKg();
        this.username = username;
    }
    public Order() {

    }

    public int getId() {
        return id;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public String getInfo() {
        return "ID: " + id + ", Name: " + nameOrder + ", Weight: " + weight +
                "kg, Delivery: " + deliveryType + ", Total Price: $" + totalPrice + ", User: " + username;
    }
}
