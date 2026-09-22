package project;

public abstract class Vehicle {
	private int id;
	private String model;
	private double baseRate;
	private boolean isAvailable;

	public Vehicle(int id, String model, double baseRate, boolean isAvailable) {
		this.id = id;
		this.model = model;
		this.baseRate = baseRate;
		this.isAvailable = isAvailable;
	}

	public abstract double calculateRentalCost(int days);
	
	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public double getBaseRate() {
		return baseRate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	
	
}