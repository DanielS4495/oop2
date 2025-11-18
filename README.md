# Hotel Booking System - OOP2 Final Project

## 📖 Project Overview
[cite_start]This project is a comprehensive **Hotel Booking System** designed to facilitate user interactions through a structured **MVC (Model-View-Controller)** architecture[cite: 2]. [cite_start]The system allows users to search for hotels, make reservations, process payments, and manage bookings, while providing Managers with tools to manage hotel assets and notifications[cite: 6, 13].

[cite_start]The core logic emphasizes separation of concerns, modularity, and scalability through the use of advanced **Design Patterns** and **SOLID principles**[cite: 2, 18].

---

## 🏗️ Architecture: MVC
[cite_start]The system consists of three main components[cite: 3, 39]:

1.  **Model (System Booking):** Represents the backend logic and data. [cite_start]It manages Users, Managers, Hotels, Reservations, and Notifications[cite: 6]. [cite_start]It acts as a Facade for the entire system[cite: 249].
2.  [cite_start]**View (UI):** The presentation layer designed to be intuitive, displaying menus, hotel lists, and booking details to the user[cite: 16, 17].
3.  [cite_start]**Controller:** The central hub that handles user input (requests) and delegates tasks to the System Booking model, ensuring the View is updated accordingly[cite: 4, 22].

---

## 🛠️ Design Patterns Implemented
The project implements multiple design patterns to solve complex architectural problems:

### 1. Singleton
* **Usage:** Implemented in the `SystemBooking` class.
* [cite_start]**Purpose:** Ensures there is only one instance of the booking system throughout the application and provides a global access point to it[cite: 121, 123].

### 2. Facade
* **Usage:** The `SystemBooking` class acts as a Facade.
* [cite_start]**Purpose:** Provides a unified interface to the complex subsystems (User management, Hotel management, Filtering, etc.), simplifying the interaction for the Controller/Client[cite: 248, 249].

### 3. Strategy
* **Usage:** Implemented in `Sorting` and `Filtering` logic.
* [cite_start]**Purpose:** Allows the system to switch between different algorithms dynamically at runtime[cite: 201].
    * [cite_start]**Sorting:** Sort by Price, Rating, Location, etc.[cite: 203].
    * [cite_start]**Filtering:** Filter by Amenities, Date, Price Range, etc.[cite: 43].

### 4. Factory
* **Usage:** `HotelFactory` and `RoomFactory`.
* [cite_start]**Purpose:** Centralizes the creation logic of Hotel and Room objects, allowing for easy extension of new types without modifying client code[cite: 43, 133].

### 5. Composite
* **Usage:** `CompositeFilter` class implementing the `Filtering` interface.
* [cite_start]**Purpose:** Allows composing multiple filters (e.g., "Price" AND "Location" AND "Rating") into a tree structure and treating them as a single filter object[cite: 266, 354].

### 6. Observer
* **Usage:** `Notification` system (e.g., `WhatsApp`, `SMS`, `Email`).
* [cite_start]**Purpose:** Defines a subscription mechanism where Users and Managers are notified automatically about events like booking confirmations or cancellations[cite: 283, 284].

---

## ✨ Key Features
* [cite_start]**User Roles:** distinct capabilities for **Users** (Search, Book, Pay) and **Managers** (Add Hotels, Manage Rooms)[cite: 12, 13].
* [cite_start]**Advanced Search:** Filter hotels by price, amenities, date, rating, and location using Composite filters[cite: 32, 358].
* [cite_start]**Sorting:** Sort results by various criteria (Price, Rating, Room Size, Top Reviews)[cite: 33, 220].
* [cite_start]**Reservation System:** Full lifecycle management including availability checks and status updates (Confirmed/Cancelled)[cite: 34, 65].
* [cite_start]**Payment Processing:** Support for multiple payment methods (`ApplePay`, `Bit`, `CreditCard`, `PayPal`)[cite: 38].
* [cite_start]**Notifications:** Automatic alerts via SMS/Email/WhatsApp upon reservation status changes[cite: 37].

---

## 📂 Class Structure Highlights
* [cite_start]`SystemBooking`: The main engine (Singleton & Facade)[cite: 39].
* [cite_start]`Person` (Abstract): Base class for `User` and `Manager`[cite: 11, 100].
* [cite_start]`Hotel` & `Room`: Entity classes managing their own availability and data[cite: 14, 15].
* [cite_start]`Filtering` (Interface) & `CompositeFilter`: Handling complex query logic using Generics[cite: 356].

---

## 🚀 Getting Started
1.  Clone the repository:
    ```bash
    git clone [https://github.com/DanielS4495/oop2.git](https://github.com/DanielS4495/oop2.git)
    ```
2.  Run the `Main.java` file to start the application.
