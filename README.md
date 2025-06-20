# 🛒 E-Commerce REST API (Spring Boot)

A simple backend REST API for an e-commerce system, built with Spring Boot and MySQL. This project handles core operations such as managing products, adding items to the cart, and placing orders.

---

## 📦 Features

- Add, update, and view products.
- Add items to a cart.
- Place and view orders.
- Exception handling for invalid inputs and duplicate entries.
- RESTful structure using `@Controller`, `@Service`, and `@Repository`.

---

## 🛠 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Maven**

---

## 🧱 Database Schema (SQL) and Instructions

```sql
CREATE DATABASE new_ecommerce;

USE new_ecommerce;

CREATE TABLE products(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  available_quantity INT,
  price DECIMAL
);

CREATE TABLE cart(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT,
  quantity INT,
  total_price DECIMAL,
  FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE user_order(
  id INT PRIMARY KEY AUTO_INCREMENT,
  product_id INT,
  quantity INT,
  total_price DECIMAL,
  FOREIGN KEY(product_id) REFERENCES products(id)
);
```

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL Server
- Git

---

### 🧪 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/ecommerce-api.git
   cd ecommerce-api
   ```

2. **Create the database**
   - Open MySQL shell or GUI (e.g., MySQL Workbench).
   - Run the SQL script above to set up the schema.

3. **Configure application properties**

   Edit `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/new_ecommerce
   spring.datasource.username=springstudent
   spring.datasource.password=springstudent
   spring.jpa.hibernate.ddl-auto=update
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Test Endpoints**

   Use Postman or cURL to interact with:

   - `http://localhost:8080/api/products`
   - `http://localhost:8080/cart`
   - `http://localhost:8080/order`

---

## 📄 License

This project is open-source and free to use.