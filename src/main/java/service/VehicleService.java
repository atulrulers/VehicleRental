package service;

import lombok.NonNull;
import model.branch.BranchInfo;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;
import utils.VehicleUtil;

import java.util.List;
import java.util.Set;

import static constants.Global.BRANCH_LIST;

public class VehicleService implements IVehicleService {

    public Boolean addVehicleInBranch(final @NonNull String branchId, final @NonNull VehicleType vehicleType,
            final @NonNull String vehicleId, final @NonNull Double rate) {
        try {
            final BranchInfo branchInfo = BRANCH_LIST.getBranchInfoMap()
                                                     .get(branchId);

            final Set<VehicleType> vehicleTypeSet = branchInfo.getVehicleTypes();
            // vehicle type allowed
            if (vehicleTypeSet.contains(vehicleType)) {
                final List<Vehicle> vehicleTypeList = branchInfo.getAvailableVehicle()
                                                                .get(vehicleType);
                final Vehicle vehicle = Vehicle.builder()
                                               .registrationNumber(vehicleId)
                                               .rate(rate)
                                               .vehicleType(vehicleType)
                                               .build();
                vehicleTypeList.add(vehicle);
                branchInfo.getAvailableVehicle()
                          .put(vehicleType, vehicleTypeList);

                final Long vehicleCount = branchInfo.getVehicleCount()
                                                    .get(vehicleType);
                branchInfo.getVehicleCount()
                          .put(vehicleType, vehicleCount + 1);
                System.out.println("Vehicle Added");
                return Boolean.TRUE;

            } else {
                System.out.println("Vehicle of this type cannot be added in this branch");
                return Boolean.FALSE;
            }

        } catch (RuntimeException ex) {
            System.out.println("Couldn't add vehicle");
            ex.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public void showAvailableVehicleInBranch(final @NonNull String branchId) {
        try {
            VehicleUtil.showAvailableVehicleInBranch(branchId, BRANCH_LIST);
        } catch (RuntimeException ex) {
            System.out.println("Something went wrong");
            ex.printStackTrace();
        }
    }
}
