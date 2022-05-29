package service;

import lombok.NonNull;
import model.vehicle.VehicleType;

import java.util.List;

public interface IBranchService {

    public Boolean addBranch(@NonNull final String branchName, @NonNull final List<VehicleType> vehicleType);

    public Boolean addVehicleTypeInBranch(@NonNull final String branchId, @NonNull final VehicleType vehicleType);

    public void showAllBranches();

    public void showBranchDetails(@NonNull final String branchId);
}
