package service;

import lombok.NonNull;
import model.vehicle.VehicleType;

public interface IVehicleService {

    public Boolean addVehicleInBranch(@NonNull final String branchId, @NonNull final VehicleType vehicleType,
            @NonNull final String vehicleId, @NonNull final Double rate);

    public void showAvailableVehicleInBranch(@NonNull final String branchId);
}
