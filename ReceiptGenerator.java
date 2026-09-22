package project;

import java.io.FileWriter;

public class ReceiptGenerator {

	public static void generateReceiptFile(String customerName, Vehicle vehicle, int days, double totalCost) {
		String fileName = customerName+"Receipt_Vehicle_" + vehicle.getId() + "_" + System.currentTimeMillis() + ".txt";

		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write("=========================================\n");
			writer.write("          VEHICLE RENTAL RECEIPT         \n");
			writer.write("=========================================\n");
			writer.write("Customer Name   : " + customerName + "\n");
			writer.write("Vehicle Type    : " + vehicle.getClass().getSimpleName() + "\n");
			writer.write("Vehicle Model   : " + vehicle.getModel() + "\n");
			writer.write("Base Rate/Day   : $" + vehicle.getBaseRate() + "\n");
			writer.write("Rental Duration : " + days + " days\n");
			
			if (vehicle instanceof Car) {
				writer.write("Specifications  : " + ((Car) vehicle).getDoors() + "\n");
				writer.write("Surcredit/Fees  : $15.0 (Flat Car Maintenance Fee)\n");
			} else if (vehicle instanceof Bike) {
				writer.write("Specifications  : " + ((Bike) vehicle).getEngineCapacity() + "\n");
				writer.write("Surcredit/Fees  : $0.0\n");
			}

			writer.write("-----------------------------------------\n");
			writer.write("TOTAL AMOUNT DUE: $" + totalCost + "\n");
			writer.write("=========================================\n");
			writer.write("Thank you for choosing our services!\n");

			System.out.println("Receipt saved successfully as: " + fileName);
		} catch (java.io.IOException e) {
			System.out.println("An error occurred while generating the receipt file.");
			e.printStackTrace();// give u the detailed message of the exception occurred
			// it shows in red color but your program will not end
		}
	}
}
