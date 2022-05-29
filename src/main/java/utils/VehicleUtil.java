package utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import model.branch.BranchInfo;
import model.branch.BranchList;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class VehicleUtil {

    public static boolean isVehicleAvailableInBranch(@NonNull final String branchId,
            @NonNull final VehicleType vehicleType, @NonNull final BranchList branchList) {
        if (Boolean.TRUE.equals(BranchUtil.isBranchExist(branchId, branchList))) {
            final BranchInfo branchInfo = branchList.getBranchInfoMap()
                                                    .get(branchId);
            final List<Vehicle> vehicleList = branchInfo.getAvailableVehicle()
                                                        .get(vehicleType);
            return !vehicleList.isEmpty();
        } else {
            System.out.println("No such branch exist!!");
            return false;
        }
    }

    public static void showAvailableVehicleInBranch(@NonNull final String branchId,
            @NonNull final BranchList branchList) {
        if (Boolean.TRUE.equals(BranchUtil.isBranchExist(branchId, branchList))) {
            final BranchInfo branchInfo = branchList.getBranchInfoMap()
                                                    .get(branchId);
            for (final VehicleType vehicleType : branchInfo.getAvailableVehicle()
                                                           .keySet()) {
                printAvailableVehicleType(branchInfo, vehicleType);
            }

        } else {
            System.out.println("No such branch exist!!");
        }
    }

    private static void printAvailableVehicleType(@NonNull final BranchInfo branchInfo,
            @NonNull final VehicleType vehicleType) {
        final List<Vehicle> vehicleList = branchInfo.getAvailableVehicle()
                                                    .get(vehicleType);
        if (vehicleList.isEmpty()) {
            System.out.printf("%s not available in %s branch%n", vehicleType.name(), branchInfo.getBranchName());
        } else {
            System.out.printf("All %s available in %s branch%n", vehicleType.name(), branchInfo.getBranchName());
            Collections.sort(vehicleList);
            for (final Vehicle vehicle : vehicleList) {
                System.out.printf("%s %.2f, ",
                        vehicle.getRegistrationNumber(),
                        vehicle.getRate());
            }
            System.out.println("");
        }
    }

}
