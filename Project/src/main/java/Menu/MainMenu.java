package Menu;

import managers.OrderManager;
import models.User;
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
                        System.out.println("Import/Export functionality coming soon...");
                        break;

                    case 7:
                        System.out.println("Report generation coming soon...");
                        break;

                    case 8:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }
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
            System.out.print("Select an option: ");
        }

        private int getUserChoice() {
            while (!in.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                in.next();
            }
            return in.nextInt();
        }
    }

