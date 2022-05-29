package service;

import lombok.NonNull;
import model.branch.BranchInfo;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;
import utils.BranchUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static constants.Global.BRANCH_LIST;

public class BranchService implements IBranchService {

    public Boolean addBranch(@NonNull final String branchName, @NonNull final List<VehicleType> vehicleTypeList) {
        try {
            final Set<VehicleType> vehicleTypeSet = new HashSet<>(vehicleTypeList);
            final Map<VehicleType, List<Vehicle>> vehicleTypeListMap = new EnumMap<>(VehicleType.class);
            Arrays.stream(VehicleType.values())
                  .forEach(v -> vehicleTypeListMap.put(v, new ArrayList<>()));

            final Map<VehicleType, Long> vehicleTypeCountMap = new EnumMap<>(VehicleType.class);
            Arrays.stream(VehicleType.values())
                  .forEach(v -> vehicleTypeCountMap.put(v, 0L));

            final BranchInfo branchInfo = BranchInfo.builder()
                                                    .branchName(branchName)
                                                    .branchId(branchName)
                                                    .vehicleTypes(vehicleTypeSet)
                                                    .availableVehicle(vehicleTypeListMap)
                                                    .bookedVehicle(new HashMap<>())
                                                    .vehicleCount(vehicleTypeCountMap)
                                                    .build();

            final Map<String, BranchInfo> branchInfoMap = BRANCH_LIST.getBranchInfoMap();
            branchInfoMap.put(branchInfo.getBranchId(), branchInfo);
            BRANCH_LIST.setBranchInfoMap(branchInfoMap);
            return Boolean.TRUE;
        } catch (RuntimeException ex) {
            System.out.println("Runtime Exception ");
            ex.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean addVehicleTypeInBranch(@NonNull final String branchId, @NonNull final VehicleType vehicleType) {
        try {
            final Map<String, BranchInfo> branchInfoMap = BRANCH_LIST.getBranchInfoMap();
            final BranchInfo branchInfo = branchInfoMap.get(branchId);
            final Set<VehicleType> vehicleTypes = branchInfo.getVehicleTypes();
            vehicleTypes.add(vehicleType);
            return Boolean.TRUE;
        } catch (RuntimeException ex) {
            return Boolean.FALSE;
        }
    }

    public void showAllBranches() {
        final Map<String, BranchInfo> branchInfoMap = BRANCH_LIST.getBranchInfoMap();
        if (branchInfoMap == null) {
            System.out.println("Something went wrong!!!");
            return;
        }
        for (final BranchInfo branchInfo : branchInfoMap.values()) {
            BranchUtil.showBranchDetails(branchInfo);
        }
    }

    public void showBranchDetails(@NonNull final String branchId) {
        final Map<String, BranchInfo> branchInfoMap = BRANCH_LIST.getBranchInfoMap();
        if (branchInfoMap == null) {
            System.out.println("Something went wrong!!!");
            return;
        }
        BranchUtil.showBranchDetails(branchInfoMap.get(branchId));
    }
}
