ProjectAdamas — Vehicle Rental System
A console-based Vehicle Rental Management System built in Java, using JDBC to persist
data in a MySQL database. Built during a QSpider internship project.
Features
View all currently available vehicles (cars and bikes)
Rent a vehicle for a chosen number of days and generate a text receipt
Return a rented vehicle
Add new vehicles (cars or bikes) to the fleet
Cost calculation differs by vehicle type (cars include a flat maintenance fee)
Tech Stack
Java (JDBC)
MySQL
MySQL Connector/J (`com.mysql.cj.jdbc.Driver`)
Project Structure
```
ProjectAdamas/
├── src/project/
│   ├── MainApp.java           # Entry point — console menu & workflow
│   ├── Vehicle.java           # Abstract base class
│   ├── Car.java                # Car subclass (extra flat fee)
│   ├── Bike.java                # Bike subclass
│   ├── VehicleDAO.java          # DAO interface
│   ├── VehicleDAOImpl.java      # JDBC implementation of VehicleDAO
│   ├── DBConnection.java        # MySQL connection helper
│   └── ReceiptGenerator.java    # Writes a .txt receipt after each rental
├── sql/schema.sql               # Database schema + sample seed data
└── .gitignore
```
Design Overview
`Vehicle` is an abstract class extended by `Car` and `Bike`, each overriding
`calculateRentalCost(int days)` with its own pricing rule. Persistence is
handled through the `VehicleDAO` interface, implemented by `VehicleDAOImpl`,
which talks to MySQL via `DBConnection`. `MainApp` drives a simple console
menu, and `ReceiptGenerator` writes a plain-text receipt to disk after every
successful rental.
Setup & Run
1. Database
Start MySQL (e.g. via XAMPP) and run the schema script:
```bash
mysql -u root -p < sql/schema.sql
```
This creates the `rental_db` database and a `vehicles` table with two sample
vehicles.
2. MySQL Connector/J
Download the MySQL Connector/J
`.jar` and add it to your classpath (or your IDE's build path).
3. Configure credentials
`src/project/DBConnection.java` defaults to:
```java
URL      = "jdbc:mysql://localhost:3306/rental_db"
USER     = "root"
PASSWORD = ""
```
Update these to match your local MySQL setup if needed.
4. Compile & run
```bash
javac -cp .:mysql-connector-j-<version>.jar -d bin src/project/*.java
java -cp bin:mysql-connector-j-<version>.jar project.MainApp
```
(On Windows, replace `:` with `;` in the classpath.)
Sample Menu
```
=== VEHICLE RENTAL SYSTEM ===
1. View Available Vehicles
2. Rent a Vehicle
3. Return a Vehicle
4. Add a New Vehicle
5. Exit
```
Notes
Receipts are saved to the working directory as
`<CustomerName>Receipt_Vehicle_<id>_<timestamp>.txt`.
This was built as a learning project during a QSpider internship to
practice OOP design, JDBC, and DAO patterns in Java.
