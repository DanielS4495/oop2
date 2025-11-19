# Hotel Booking System - OOP2 Final Project

## 📖 Project Overview
This project is a comprehensive **Hotel Booking System** designed to facilitate user interactions through a structured **MVC (Model-View-Controller)** architecture. The system allows users to search for hotels, make reservations, process payments, and manage bookings, while providing Managers with tools to manage hotel assets and notifications.

The core logic emphasizes separation of concerns, modularity, and scalability through the use of advanced **Design Patterns** and **SOLID principles**.

---

## 🏗️ Architecture: MVC
The system consists of three main components:

1.  **Model (System Booking):** Represents the backend logic and data. It manages Users, Managers, Hotels, Reservations, and Notifications. It acts as a Facade for the entire system.
2.  **View (UI):** The presentation layer designed to be intuitive, displaying menus, hotel lists, and booking details to the user.
3.  **Controller:** The central hub that handles user input (requests) and delegates tasks to the System Booking model, ensuring the View is updated accordingly.

---

## 🛠️ Design Patterns Implemented
The project implements multiple design patterns to solve complex architectural problems:

### 1. Singleton
* **Usage:** Implemented in the `SystemBooking` class.
* **Purpose:** Ensures there is only one instance of the booking system throughout the application and provides a global access point to it.

### 2. Facade
* **Usage:** The `SystemBooking` class acts as a Facade.
* **Purpose:** Provides a unified interface to the complex subsystems (User management, Hotel management, Filtering, etc.), simplifying the interaction for the Controller/Client.

### 3. Strategy
* **Usage:** Implemented in `Sorting` and `Filtering` logic.
* **Purpose:** Allows the system to switch between different algorithms dynamically at runtime.
    * **Sorting:** Sort by Price, Rating, Location, etc..
    * **Filtering:** Filter by Amenities, Date, Price Range, etc..

### 4. Factory
* **Usage:** `HotelFactory` and `RoomFactory`.
* **Purpose:** Centralizes the creation logic of Hotel and Room objects, allowing for easy extension of new types without modifying client code.

### 5. Composite
* **Usage:** `CompositeFilter` class implementing the `Filtering` interface.
* **Purpose:** Allows composing multiple filters (e.g., "Price" AND "Location" AND "Rating") into a tree structure and treating them as a single filter object.

### 6. Observer
* **Usage:** `Notification` system (e.g., `WhatsApp`, `SMS`, `Email`).
* **Purpose:** Defines a subscription mechanism where Users and Managers are notified automatically about events like booking confirmations or cancellations.

---

## ✨ Key Features
* **User Roles:** distinct capabilities for **Users** (Search, Book, Pay) and **Managers** (Add Hotels, Manage Rooms).
* **Advanced Search:** Filter hotels by price, amenities, date, rating, and location using Composite filters.
* **Sorting:** Sort results by various criteria (Price, Rating, Room Size, Top Reviews).
* **Reservation System:** Full lifecycle management including availability checks and status updates (Confirmed/Cancelled).
* **Payment Processing:** Support for multiple payment methods (`ApplePay`, `Bit`, `CreditCard`, `PayPal`).
* **Notifications:** Automatic alerts via SMS/Email/WhatsApp upon reservation status changes.

---

## 📂 Class Structure Highlights
* `SystemBooking`: The main engine (Singleton & Facade).
* `Person` (Abstract): Base class for `User` and `Manager`.
* `Hotel` & `Room`: Entity classes managing their own availability and data.
* `Filtering` (Interface) & `CompositeFilter`: Handling complex query logic using Generics.

---

## 🚀 Getting Started
1.  Clone the repository:
    ```bash
    git clone https://github.com/DanielS4495/oop2.git
    ```
2.  Run the `Main.java` file to start the application.
