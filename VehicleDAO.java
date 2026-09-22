package project;

import java.util.List;

/**
 * Data-access contract for Vehicle persistence operations.
 * Implemented by {@link VehicleDAOImpl} against a MySQL backend.
 */
public interface VehicleDAO {

	void addVehicle(Vehicle vehicle);

	List<Vehicle> getAllAvailableVehicles();

	Vehicle getVehicleById(int vehicleId);

	boolean rentVehicle(int vehicleId);

	boolean returnVehicle(int vehicleId);
}
