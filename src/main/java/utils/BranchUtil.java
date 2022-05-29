package utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import model.branch.BranchInfo;
import model.branch.BranchList;

@UtilityClass
public class BranchUtil {

    public static Boolean isBranchExist(@NonNull final String branchId, @NonNull final BranchList branchList) {
        return branchList.getBranchInfoMap()
                         .containsKey(branchId);
    }

    public static void showBranchDetails(@NonNull final BranchInfo branchInfo) {
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%s %s %n", branchInfo.getBranchId(), branchInfo.getBranchName());
        System.out.printf("Vehicles Types %s%n", branchInfo.getVehicleTypes()
                                                           .toString());
        System.out.printf("Available Vehicles %s%n", branchInfo.getAvailableVehicle()
                                                               .toString());
        System.out.printf("Vehicles Count %s%n", branchInfo.getVehicleCount()
                                                           .toString());
        System.out.printf("Booked Vehicles %s%n", branchInfo.getBookedVehicle()
                                                            .toString());
        System.out.println("----------------------------------------------------------------");
    }
}
