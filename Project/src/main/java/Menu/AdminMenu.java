package Menu;

import managers.UserManager;
import models.User;
import java.util.Scanner;

public class AdminMenu {
    private final Scanner scanner;
    private final UserManager userManager;

    public AdminMenu(Scanner scanner, UserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    public void start() {
        while (true) {
            System.out.println("\n    ADMIN MENU    ");
            System.out.println("1. View all users");
            System.out.println("2. Delete user");
            System.out.println("3. Exit...");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (User user : userManager.getUsers()) {
                        System.out.println(user);
                    }
                    break;
                case 2:
                    System.out.print("Enter username to delete: ");
                    String nameToDelete = scanner.next();
                    userManager.deleteUser(nameToDelete);
                    System.out.println("User deleted (if existed).");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

