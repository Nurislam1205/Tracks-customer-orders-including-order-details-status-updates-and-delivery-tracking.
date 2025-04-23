package managers;

import models.Order;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private DeliveryType deliveryType;
    private final String FILE_NAME = "orders.ser";

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        saveOrdersToFile();
    }

    public void createOrder(Scanner scanner, String username) {
        System.out.println("Please enter the name of the order you would like to create: ");
        String nameOrder = scanner.next();

        System.out.println("Please enter the weight of the order: ");
        while (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a valid weight (e.g., 5.5): ");
            scanner.next();
        }
        float weight = scanner.nextFloat();

        if (weight <= 0) {
            System.out.println("Weight must be greater than 0.");
            return;
        }
        scanner.nextLine();

        System.out.println("Please choose the delivery type: (1-AVIA, 2-TRUCK, 3-TRAIN) ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                deliveryType = DeliveryType.AVIA;
                break;
            case 2:
                deliveryType = DeliveryType.TRUCK;
                break;
            case 3:
                deliveryType = DeliveryType.TRAIN;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        Order order = new Order(nameOrder, weight, deliveryType, username);
        orders.add(order);
        saveOrdersToFile();
        System.out.println(order.getInfo());
    }

    public void updateOrder(Scanner in, String username) {
        System.out.println("Please enter the ID of the order you would like to update: ");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid order ID.");
            in.next();
        }
        int choice = in.nextInt();
        in.nextLine();

        if (orders.isEmpty()) {
            System.out.println("No orders to update.");
            return;
        }

        Order orderToUpdate = null;
        for (Order order : orders) {
            if (order.getId() == choice && order.getUsername().equals(username)) {
                orderToUpdate = order;
                break;
            }
        }

        if (orderToUpdate == null) {
            System.out.println("Not found or doesn't belong to you.");
            return;
        }

        System.out.println("You selected order: " + orderToUpdate.getInfo());
        System.out.println("What would you like to update?");
        System.out.println("1. Update name");
        System.out.println("2. Update weight");
        System.out.println("3. Update delivery type");
        System.out.print("Select an option: ");

        while (!in.hasNextInt()) {
            System.out.println("Invalid choice. Please enter a valid option.");
            in.next();
        }
        int choice2 = in.nextInt();

        switch (choice2) {
            case 1:
                System.out.println("New name:");
                String newName = in.next();
                orderToUpdate.setNameOrder(newName);
                break;
            case 2:
                System.out.println("New weight:");
                float newWeight = in.nextFloat();
                orderToUpdate.setWeight(newWeight);
                break;
            case 3:
                System.out.println("New delivery type:");
                System.out.println("1. AVIA");
                System.out.println("2. TRUCK");
                System.out.println("3. TRAIN");
                int choice3 = in.nextInt();
                in.nextLine();
                switch (choice3) {
                    case 1:
                        orderToUpdate.setDeliveryType(DeliveryType.AVIA);
                        break;
                    case 2:
                        orderToUpdate.setDeliveryType(DeliveryType.TRUCK);
                        break;
                    case 3:
                        orderToUpdate.setDeliveryType(DeliveryType.TRAIN);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        orderToUpdate.setTotalPrice(orderToUpdate.getWeight() * orderToUpdate.getDeliveryType().getCostPerKg());
        saveOrdersToFile();
        System.out.println("Order updated successfully: " + orderToUpdate.getInfo());
    }

    public void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    private List<Order> loadOrdersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public OrderManager() {
        orders = loadOrdersFromFile();
    }

    public void showUserOrders(String username) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getUsername().equals(username)) {
                System.out.println(order.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders for user: " + username);
        }
    }

    public void deleteOrder(Scanner in, String username) {
        System.out.println("Please enter the ID of the order you would like to delete: ");
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid order ID.");
            in.next();
        }
        int choice = in.nextInt();
        in.nextLine();

        if (orders.isEmpty()) {
            System.out.println("No orders to delete.");
            return;
        }

        Order orderToDelete = null;
        for (Order order : orders) {
            if (order.getId() == choice && order.getUsername().equals(username)) {
                orderToDelete = order;
                break;
            }
        }

        if (orderToDelete == null) {
            System.out.println("Order not found or doesn't belong to you.");
            return;
        }

        orders.remove(orderToDelete);
        saveOrdersToFile();
        System.out.println("Order deleted successfully.");
    }

    public enum DeliveryType {
        AVIA(9.1f, 6),
        TRUCK(4.3f, 14),
        TRAIN(1.5f, 20);

        private final float costPerKg;
        private final float Days;

        DeliveryType(float costPerKg, float Days) {
            this.costPerKg = costPerKg;
            this.Days = Days;
        }

        public float getCostPerKg() {
            return costPerKg;
        }

        public float getDays() {
            return Days;
        }

        @JsonCreator
        public static DeliveryType fromString(String key) {
            return DeliveryType.valueOf(key.toUpperCase());
        }
    }
}

