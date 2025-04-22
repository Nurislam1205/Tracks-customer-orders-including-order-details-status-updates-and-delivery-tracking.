package managers;

import models.User;
import java.io.*;
import java.util.*;

public class UserManager {
    private final String FILE_NAME = "users.ser";
    private List<User> users;

    public UserManager() {
        users = loadUsersFromFile();
        if (users.isEmpty()) {
            users.add(new User("admin", "admin123", "admin"));
            saveUsersToFile();
        }
    }

    public User login(Scanner scanner) {
        System.out.print("Login: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    public void register(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.next();

        for (User user : users) {
            if (user.getUserName().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.next();

        User newUser = new User(username, password, "user");
        users.add(newUser);
        saveUsersToFile();
        System.out.println("User registered.");
    }

    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private List<User> loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUserName().equals(username));
        saveUsersToFile();
    }
}
