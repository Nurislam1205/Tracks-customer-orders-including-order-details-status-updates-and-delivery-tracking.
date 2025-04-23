package managers;

import models.Order;
import models.User;

import java.util.List;

public class ReportGenerator {

    public static void generateUserReport(List<User> users) {
        System.out.println("---- User Report ----");
        System.out.println("Total users: " + users.size());
        for (User user : users) {
            System.out.println("User: " + user.getUserName() + " | Role: " + user.getRole());
        }
    }

    public static void generateOrderReport(List<Order> orders) {
        System.out.println("---- Order Report ----");
        System.out.println("Total orders: " + orders.size());
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId() +
                    " | User: " + order.getUsername() +
                    " | Weight: " + order.getWeight() + "kg" +
                    " | Delivery: " + order.getDeliveryType() +
                    " | Total Price: $" + order.getTotalPrice());
        }
    }

    public static void generateUserActivityReport(List<User> users, List<Order> orders) {
        System.out.println("---- User Activity Report ----");
        for (User user : users) {
            long userOrders = orders.stream()
                    .filter(order -> order.getUsername().equals(user.getUserName()))
                    .count();
            System.out.println("User: " + user.getUserName() +
                    " | Role: " + user.getRole() +
                    " | Orders: " + userOrders);
        }
    }
}