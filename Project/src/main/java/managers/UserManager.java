package managers;

import models.User;
import java.io.*;
import java.util.*;

public class UserManager {
    private final String FILE_NAME = "users.ser";
    private List<User> users;

    public UserManager() {
        this.users = loadUsersFromFile();

        if (users.isEmpty()) {
            users.add(new User("admin", "admin123", "admin"));
            saveUsersToFile();
        }
    }

    // Логика для авторизации
    public User login(Scanner scanner) {
        System.out.print("Login: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        Optional<User> userOptional = users.stream()
                .filter(user -> user.getUserName().equals(username) && user.getPassword().equals(password))
                .findFirst();

        if (userOptional.isPresent()) {
            System.out.println("Login successful!");
            return userOptional.get();
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }

    // Регистрация нового пользователя
    public void register(Scanner scanner) {
        System.out.print("Enter new username: ");
        String username = scanner.next();

        // Проверка на существование такого имени пользователя
        if (users.stream().anyMatch(user -> user.getUserName().equals(username))) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.next();

        // Добавляем нового пользователя
        User newUser = new User(username, password, "user");
        users.add(newUser);
        saveUsersToFile();
        System.out.println("User registered.");
    }

    // Сохранение списка пользователей в файл
    private void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Загрузка списка пользователей из файла
    private List<User> loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>(); // Если файл не существует, возвращаем пустой список
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
            return new ArrayList<>(); // Возвращаем пустой список при ошибке
        }
    }

    // Получение списка всех пользователей
    public List<User> getUsers() {
        return users;
    }

    // Удаление пользователя
    public void deleteUser(String username) {
        boolean removed = users.removeIf(user -> user.getUserName().equals(username));
        if (removed) {
            saveUsersToFile();
            System.out.println("User deleted.");
        } else {
            System.out.println("User not found.");
        }
    }
}
