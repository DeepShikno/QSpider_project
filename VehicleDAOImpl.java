package project;

import java.sql.*;
import java.util.*;

public class VehicleDAOImpl implements VehicleDAO {
	
	@Override
	public void addVehicle(Vehicle vehicle) {
		String query = "INSERT INTO vehicles (type, model, base_rate, is_available, extra_feature) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			if (vehicle instanceof Car) {
				stmt.setString(1, "Car");
				stmt.setString(5, ((Car) vehicle).getDoors());
			} else if (vehicle instanceof Bike) {
				stmt.setString(1, "Bike");
				stmt.setString(5, ((Bike) vehicle).getEngineCapacity());
			}

			stmt.setString(2, vehicle.getModel());
			stmt.setDouble(3, vehicle.getBaseRate());
			stmt.setBoolean(4, vehicle.isAvailable());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public List<Vehicle> getAllAvailableVehicles() {
		List<Vehicle> vehicles = new ArrayList<>(); 
		String query = "SELECT * FROM vehicles WHERE is_available = true";
// we are checking if its available because either the owner has not having 
// the vehicle itself or someone has rented it.
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");
				String model = rs.getString("model");
				double rate = rs.getDouble("base_rate");
				boolean available = rs.getBoolean("is_available");
				String extra = rs.getString("extra_feature");
				if ("Car".equalsIgnoreCase(type)) {
					vehicles.add(new Car(id, model, rate, available, extra));
				} else {
					vehicles.add(new Bike(id, model, rate, available, extra));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}

	@Override
	public boolean rentVehicle(int vehicleId) {
		return updateAvailability(vehicleId, false);
	}

	@Override
    public boolean returnVehicle(int vehicleId) {
       
        Vehicle vehicle = getVehicleById(vehicleId);
        
        //check if it exists and if it is actually rented out
        if (vehicle == null) {
            System.out.println("Error: Vehicle with ID " + vehicleId + " does not exist.");
            return false;
        }
        
        if (vehicle.isAvailable()) {
            System.out.println("Warning: This vehicle is already available in the garage! You cannot return it.");
            return false;
        }
        
        return updateAvailability(vehicleId, true);
    }

	@Override
	public Vehicle getVehicleById(int vehicleId) {
		String query = "SELECT * FROM vehicles WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, vehicleId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String type = rs.getString("type");
					String model = rs.getString("model");
					double rate = rs.getDouble("base_rate");
					boolean available = rs.getBoolean("is_available");
					String extra = rs.getString("extra_feature");

					if ("Car".equalsIgnoreCase(type)) {
						return new Car(vehicleId, model, rate, available, extra);
					} else {
						return new Bike(vehicleId, model, rate, available, extra);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private boolean updateAvailability(int id, boolean status) {
		String query = "UPDATE vehicles SET is_available = ? WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setBoolean(1, status);
			stmt.setInt(2, id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
