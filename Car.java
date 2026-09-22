package project;

public class Car extends Vehicle {
	private String doors;

	public Car(int id, String model, double baseRate, boolean isAvailable, String doors) {
		super(id, model, baseRate, isAvailable);
		this.doors = doors;
	}
	
	@Override
	public double calculateRentalCost(int days) {
		return (getBaseRate() * days) + 15.0; // default base rate for Car
	}

	public String getDoors() {
		return doors;
	}
}
