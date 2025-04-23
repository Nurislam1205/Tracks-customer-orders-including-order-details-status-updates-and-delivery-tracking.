# Order Management System (Java, Console-Based)

## Description
This is a console-based CRUD system for managing customer orders. It includes user authentication, role-based access (admin/user), and supports both file and database storage.

## Features
- User registration and login with roles (admin, user)
- Admin panel for viewing and deleting users
- Create, read, update, and delete orders
- Each order includes weight, delivery type, and total cost calculation
- Save/load orders from JSON file
- Persist users in SQL database (`users` table)

## Technologies Used
- Java (core)
- MySQL (JDBC)
- JSON (Jackson)
- File serialization (`.ser` for legacy data)
- Console interface (Scanner)

Project Structure
models/
User – Represents a user with ID, username, password, and role.

Order – Represents an order with ID, item name, weight, delivery type, associated user, and total price.

managers/
UserManager – Handles user registration, login, deletion, and file/database synchronization.

OrderManager – Manages creating, reading, updating, and deleting orders.

FileManager – Handles reading from and writing to JSON files.

dbManager – Connects to SQL database and interacts with the users table.

ReportManager – Generates summary reports for orders.

Menu/
Main – Main entry point and navigation menu.

AdminMenu – Admin-specific interface for managing user accounts.



## Getting Started
### Prerequisites
- Java 11 or above
- MySQL server running with a `userdatabase` schema

### Database Setup
```sql
CREATE DATABASE IF NOT EXISTS userdatabase;
USE userdatabase;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'user'
);
INSERT INTO users (username, password, role) VALUES ('admin', 'Nurislam06=na', 'admin');
```

### Compile & Run
```bash
javac -d out src/**/*.java
java -cp out Main
```

## Input/Output Files
- `orders.json` – stores list of orders in JSON
- `users.ser` – legacy binary file with serialized user list (optional)

## Example User Roles
- **admin / Nurislam06=na** — has access to admin menu
- **any user** — can manage only their own orders

## Future Improvements
- Add GUI (JavaFX/Swing)
- Implement email notifications
- Add filtering/sorting for orders
- Add order status tracking (e.g., "pending", "delivered")

## Author
Nurislam (Student Project)
![image](https://github.com/user-attachments/assets/e8947447-b04d-44a9-a014-795f5e1d4bc8)
!![image](https://github.com/user-attachments/assets/957436a6-e975-4c64-8347-acd0e897f9cf)
![image](https://github.com/user-attachments/assets/3a3284d7-823c-4262-8691-be7ca07ee47e)
![image](https://github.com/user-attachments/assets/656b6656-e3c4-41af-9326-6b48760bc7f8)
![image](https://github.com/user-attachments/assets/5024327b-8996-4473-a3ef-63c9b08470b1)
![image](https://github.com/user-attachments/assets/d59ea13d-886a-4e67-b0b0-b65a5a612f2e)

![image](https://github.com/user-attachments/assets/c7e347a6-acfe-4e31-a197-173e2109db00)
![image](https://github.com/user-attachments/assets/4c366bed-305b-4bb7-b7fe-974681bf89e9)




