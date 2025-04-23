package Menu;

import managers.FileManager;
import managers.NewReport;
import managers.OrderManager;
import managers.ReportGenerator;
import models.Order;
import models.User;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final Scanner in;
    private final OrderManager orderManager;
    private final User currentUser;

    public MainMenu(Scanner in, OrderManager orderManager, User currentUser) {
        this.in = in;
        this.orderManager = orderManager;
        this.currentUser = currentUser;
    }
    private void printMenu() {
        System.out.println("\n         ORDER MENU          ");
        System.out.println("1. Create order");
        System.out.println("2. Review orders");
        System.out.println("3. Update order");
        System.out.println("4. Remove order");
        System.out.println("5. Save to a file");
        System.out.println("6. Import / Export");
        System.out.println("7. Generate a report");
        System.out.println("8. Exit");
        System.out.println("9. View type of delivery");
        System.out.println("10. View deliveries");
        System.out.print("Select an option: ");
    }

    private int getUserChoice() {
        while (!in.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            in.next();
        }
        return in.nextInt();
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    orderManager.createOrder(in, currentUser.getUserName());
                    break;

                case 2:
                    orderManager.showUserOrders(currentUser.getUserName());
                    break;

                case 3:
                    orderManager.updateOrder(in, currentUser.getUserName());
                    break;

                case 4:
                    orderManager.deleteOrder(in, currentUser.getUserName());
                    break;

                case 5:
                    orderManager.saveOrdersToFile();
                    System.out.println("Orders saved successfully.");
                    break;

                case 6:
                    System.out.println("1 - Export orders to JSON");
                    System.out.println("2 - Import orders from JSON");
                    int option = in.nextInt();
                    in.nextLine();

                    switch (option) {
                        case 1:
                            FileManager.exportOrdersToJSON(orderManager.getOrders(), "orders.json");
                            break;
                        case 2:
                            List<Order> imported = FileManager.importOrdersFromJSON("orders.json");
                            orderManager.setOrders(imported);
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    break;

                case 7:
                    System.out.println("1 - Generate order report");
                    System.out.println("2 - Generate user activity report (admin only)");
                    int reportChoice = in.nextInt();
                    in.nextLine();

                    switch(reportChoice) {
                        case 1:
                            ReportGenerator.generateOrderReport(orderManager.getOrders());
                            break;
                        case 2:
                            if(currentUser.getRole().equals("admin")) {

                                System.out.println("This feature requires admin privileges");
                            } else {
                                System.out.println("Access denied - admin only");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    break;

                case 8:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                case 9:
                    System.out.println("Type of delivery");
                    NewReport.chooseDeliveryTypeReport(in,orderManager.getOrders());
                    break;
                case 10:
                    System.out.println("Types of all deliveries");
                    NewReport.printAllDeliveryTypes(orderManager.getOrders());
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}