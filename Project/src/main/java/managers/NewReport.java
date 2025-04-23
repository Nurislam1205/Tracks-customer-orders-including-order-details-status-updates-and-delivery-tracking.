package managers;

import models.Order;
import java.util.List;
import java.util.Scanner;

public class NewReport {

    public static void chooseDeliveryTypeReport(Scanner in, List<Order> orders) {
        System.out.println("What kind of delivery do you want to see:\n" +
                "1: AVIA\n" +
                "2: TRUCK\n" +
                "3: TRAIN");

        int choice = in.nextInt();
        OrderManager.DeliveryType selectedType = null;

        switch (choice) {
            case 1:
                selectedType = OrderManager.DeliveryType.AVIA;
                break;
            case 2:
                selectedType = OrderManager.DeliveryType.TRUCK;
                break;
            case 3:
                selectedType = OrderManager.DeliveryType.TRAIN;
                break;
            default:
                System.out.println("Invalid input.");
                return;
        }

        System.out.println("Orders with delivery type " + selectedType + ":");
        for (Order order : orders) {
            if (order.getDeliveryType() == selectedType) {
                System.out.println("Order #" + order.getId() + ": " + order.getInfo());
            }
        }
    }

    public static void printAllDeliveryTypes(List<Order> orders) {
        System.out.println("Delivery types of all orders:");
        for (Order order : orders) {
            System.out.println("Order #" + order.getId() + " - " + order.getDeliveryType());
        }
    }
}
