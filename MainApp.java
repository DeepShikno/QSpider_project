package project;

import java.util.*;

public class MainApp {
	public static void main(String[] args) {
		VehicleDAO dao = new VehicleDAOImpl();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== VEHICLE RENTAL SYSTEM ===");
			System.out.println("1. View Available Vehicles");
			System.out.println("2. Rent a Vehicle");
			System.out.println("3. Return a Vehicle");
			System.out.println("4. Add a New Vehicle");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); //clear scanner buffer which is kind of a bug we have to deal with

			switch (choice) {
			case 1:
				List<Vehicle> availableVehicles = dao.getAllAvailableVehicles();
				if (availableVehicles.isEmpty()) {
					System.out.println("No vehicles available for rent right now.");
				} else {
					System.out.println("\nAvailable Vehicles:");
					for (Vehicle v : availableVehicles) {
						System.out.println("ID: " + v.getId() + " | " + v.getModel() + " ("
								+ v.getClass().getSimpleName() + ") | Rate/Day: $" + v.getBaseRate());
					}
				}
				break;

			case 2:
				System.out.print("Enter your Name: ");
				String customerName = scanner.nextLine();
				System.out.print("Enter the Vehicle ID you want to rent: ");
				int rentId = scanner.nextInt();
				System.out.print("Enter rental duration in days: ");
				int days = scanner.nextInt();

				Vehicle selectedVehicle = dao.getVehicleById(rentId);
				if (selectedVehicle != null && selectedVehicle.isAvailable()) {
					dao.rentVehicle(rentId);
					double totalCost = selectedVehicle.calculateRentalCost(days); 

					System.out.println("Successfully rented! Total estimated cost: $" + totalCost);

					//creates a bill for the Vehicle taken
					ReceiptGenerator.generateReceiptFile(customerName, selectedVehicle, days, totalCost);
				} else {
					System.out.println("Vehicle not found or is already occupied.");
				}
				break;

			case 3:
				System.out.print("Enter the Vehicle ID you are returning: ");
				int returnId = scanner.nextInt();
				if (dao.returnVehicle(returnId)) {
					System.out.println("Vehicle returned successfully!");
				} else {
					System.out.println("Failed to return vehicle. Verify the ID.");
				}
				break;

			case 4:
				System.out.print("Enter vehicle type (Car/Bike): ");
				String type = scanner.nextLine();
				System.out.print("Enter model name: ");
				String model = scanner.nextLine();
				System.out.print("Enter base rate per day: ");
				double rate = scanner.nextDouble();
				scanner.nextLine();

				if ("Car".equalsIgnoreCase(type)) {
					System.out.print("Enter number of doors (e.g., 4 Doors): ");
					String doors = scanner.nextLine();
					dao.addVehicle(new Car(0, model, rate, true, doors));
				} else {
					System.out.print("Enter engine capacity (e.g., 500cc): ");
					String engine = scanner.nextLine();
					dao.addVehicle(new Bike(0, model, rate, true, engine));
				}
				System.out.println("Vehicle added to database!");
				break;

			case 5:
				System.out.println("Exiting the application. Goodbye!");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Invalid selection. Try again.");
			}
		}
	}

	
	

}
