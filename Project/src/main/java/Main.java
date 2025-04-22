import Menu.AdminMenu;
import Menu.MainMenu;
import managers.OrderManager;
import managers.UserManager;
import models.User;
import services.userService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User currentUser = null;
        System.out.println("1-Login");
        System.out.println("2-Register");
        System.out.println("3-Exit");
        int choice = in.nextInt();
        in.nextLine();



        switch (choice) {
            case 1:
                while (currentUser == null) {
                    System.out.print("Enter your login: ");
                    String login = in.nextLine();
                    System.out.print("Enter the password: ");
                    String pass = in.nextLine();

                    currentUser = userService.login(login, pass);
                    if (currentUser == null) {
                        System.out.println("Invalid login or password. Try again.");
                    }
                }

                System.out.println("Welcome " + currentUser.getUserName());
                switch (currentUser.getRole()) {
                    case "admin":
                        System.out.println("You are logged in as an admin");
                        AdminMenu adminMenu = new AdminMenu(in,new UserManager());
                        adminMenu.start();
                        return;
                    case "user":
                        System.out.println("You are logged in as a user");
                        break;
                    default:
                        System.out.println("Unknown role");
                }
                break;
            case 2:
                boolean registered = false;
                while (!registered) {
                    System.out.print("Enter your login: ");
                    String login = in.nextLine();
                    System.out.print("Enter the password: ");
                    String pass = in.nextLine();

                    if (userService.register(login, pass)) {
                        currentUser = new User(login, pass, "user");
                        System.out.println("Register successful\nWelcome " + currentUser.getUserName());
                        registered = true;
                    } else {
                        System.out.println("Register failed. Try again.");
                    }
                }
                break;


            case 3:
                System.out.println("Exit Program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        OrderManager orderManager = new OrderManager();
        MainMenu menu = new MainMenu(in, orderManager, currentUser);
        menu.start();


    }
}