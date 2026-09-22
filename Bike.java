package project;

public class Bike extends Vehicle {
	private String engineCapacity;

	public Bike(int id, String model, double baseRate, boolean isAvailable, String engineCapacity) {
		super(id, model, baseRate, isAvailable);
		this.engineCapacity = engineCapacity;
	}

	@Override
	public double calculateRentalCost(int days) {
		return getBaseRate() * days;
	}

	public String getEngineCapacity() {
		return engineCapacity;
	}
}
