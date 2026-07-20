# JavaDevelopment-Task1-OnlineReservationSystem

## Objective

The objective of this project is to develop a GUI-based Online Reservation System that allows users to securely log in, book train tickets, and cancel reservations using a PNR number. The application demonstrates Java GUI development, database connectivity using JDBC, and CRUD operations with SQLite.

---

# Tools & Technologies Used

- Java 17+
- Java Swing
- JDBC
- SQLite
- IntelliJ IDEA / VS Code
- Maven

---

# Project Features

## User Authentication

The application begins with a secure login page.

Features:
- Username validation
- Password authentication
- Error message for invalid credentials
- Successful login redirects to the dashboard

---

## Dashboard

The dashboard provides quick access to all major functionalities.

Available Options:

- Book Ticket
- Cancel Ticket
- View Reservations
- Logout

---

## Train Reservation

Users can reserve train tickets by entering passenger details.

Reservation Details Include:

- Passenger Name
- Age
- Gender
- Train Number
- Train Name
- Source Station
- Destination Station
- Travel Date
- Class Type
- Seat Preference

After successful booking:

- A unique PNR Number is generated.
- Reservation details are stored in the SQLite database.

---

## Ticket Cancellation

Users can cancel booked tickets using the generated PNR Number.

Process:

- Enter PNR Number
- Validate Reservation
- Delete Reservation
- Display Confirmation Message

---

## Database Integration

The application uses SQLite through JDBC.

Database stores:

- Login Credentials
- Passenger Details
- Reservation Information
- Generated PNR Numbers

---

# Project Workflow

### Step 1 – Login

The user enters a valid username and password.

↓

### Step 2 – Dashboard

The dashboard appears after successful authentication.

↓

### Step 3 – Reservation

The user fills in reservation details.

↓

### Step 4 – Ticket Booking

The reservation is stored in the database and a unique PNR is generated.

↓

### Step 5 – Cancellation

The user can cancel an existing reservation using the generated PNR.

---

# Database Tables

## Users Table

Stores login credentials.

Fields:

- Username
- Password

---

## Reservations Table

Stores reservation details.

Fields:

- PNR Number
- Passenger Name
- Age
- Gender
- Train Number
- Train Name
- Source
- Destination
- Travel Date
- Class
- Seat Preference

---

# Validation Implemented

The application validates:

- Empty fields
- Invalid login credentials
- Duplicate reservations (if applicable)
- Invalid PNR during cancellation
- Required input fields

---

# Technologies Explained

## Java Swing

Used for developing the graphical user interface.

---

## JDBC

Used to connect Java applications with the SQLite database.

---

## SQLite

Lightweight relational database used to store reservation records.

---

## Maven

Used for dependency management and project build.

---
# Screenshots

## Login Page
## 


The login page allows users to enter their username and password to access the Online Reservation System.

---

## Dashboard

![Dashboard](screenshots/dashboard.png)

The dashboard provides options to book tickets, cancel reservations, and manage the system.

---

## Reservation Form

![Reservation Form](screenshots/reservation.png)

Users can enter passenger details, select train information, and reserve tickets.

---

## Booking Successful

![Booking Successful](screenshots/booking_success.png)

After a successful reservation, the system generates a unique PNR number.

---

## Cancellation Page

![Cancellation Page](screenshots/cancellation.png)

Users can cancel their reservation by entering the generated PNR number.

---

## Ticket Cancelled

![Ticket Cancelled](screenshots/cancelled.png)

The application confirms that the reservation has been cancelled successfully.

---

# How to Run

### Prerequisites

- Java JDK 17 or above
- Maven
- SQLite
- IntelliJ IDEA or VS Code

### Steps

1. Clone this repository.

```
git clone https://github.com/Dhandapanikeerthana/OIBSIP.git
```

2. Open the project in IntelliJ IDEA or VS Code.

3. Install Maven dependencies.

4. Run the main Java application.

5. Login using valid credentials.

6. Start booking or cancelling tickets.

---

# Project Structure

```
JavaDevelopment-Task1-OnlineReservationSystem/
│
├── src/
├── db/
├── lib/
├── screenshots/
├── reservation.db
├── pom.xml
├── run.bat
└── README.md
```

---

# Learning Outcomes

Through this project, I gained practical experience in:

- Java Swing GUI Development
- Event Handling
- JDBC Database Connectivity
- SQLite Database Management
- CRUD Operations
- Object-Oriented Programming
- Exception Handling
- Maven Project Structure
- Software Development Best Practices

---

# Future Enhancements

Possible improvements include:

- User Registration
- Admin Panel
- Train Search
- Payment Gateway Integration
- Email/SMS Ticket Confirmation
- PDF Ticket Generation
- Seat Availability Tracking
- Journey History
- Password Encryption
- Online Database Support (MySQL)

---

# Files Included

```
JavaDevelopment-Task1-OnlineReservationSystem/
│
├── src/
├── db/
├── lib/
├── screenshots/
├── reservation.db
├── pom.xml
├── run.bat
└── README.md
```

---

# Author

**Keerthana Dhandapani**

B.E. Computer Science and Engineering

Saveetha Engineering College

Oasis Infobyte Java Development Internship

---

# Conclusion

This project successfully demonstrates a complete Online Reservation System developed using Java Swing and SQLite. The application provides secure user authentication, train ticket booking, reservation management, and ticket cancellation through an intuitive graphical interface. It showcases core Java concepts, GUI design, database integration, and real-world software development practices, making it a valuable learning experience in desktop application development.

---

# References

- Java Documentation  
  https://docs.oracle.com/javase/

- Java Swing Tutorial  
  https://docs.oracle.com/javase/tutorial/uiswing/

- SQLite Documentation  
  https://www.sqlite.org/docs.html

- JDBC Documentation  
  https://docs.oracle.com/javase/tutorial/jdbc/

- Maven Documentation  
  https://maven.apache.org/
