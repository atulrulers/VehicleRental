package model.branch;

import lombok.Builder;
import lombok.Data;
import model.vehicle.BookedVehicle;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@Data
public class BranchInfo {
    private String                          branchId;
    private String                          branchName;
    private Address                         address;
    private Set<VehicleType>                vehicleTypes;
    private Map<VehicleType, List<Vehicle>> availableVehicle; // current vehicle types available
    private Map<VehicleType, Long>          vehicleCount; // 10 cars
    private Map<String, BookedVehicle>      bookedVehicle;

}
