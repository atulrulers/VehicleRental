package service;

import lombok.NonNull;
import model.branch.BranchInfo;
import model.vehicle.BookedVehicle;
import model.vehicle.BookingDetails;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;
import utils.TimeStampUtil;
import utils.VehicleUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static constants.Global.BRANCH_LIST;

public class BookingService implements IBookingService {

    private final static String ERROR_MESSAGE = "Something went wrong";

    public BookingDetails bookVehicle(@NonNull final String branchId, @NonNull final VehicleType vehicleType,
            @NonNull final Long bookingHours) {
        try {
            if (VehicleUtil.isVehicleAvailableInBranch(branchId, vehicleType, BRANCH_LIST)) {

                final BranchInfo branchInfo = BRANCH_LIST.getBranchInfoMap()
                                                         .get(branchId);
                final List<Vehicle> availableVehicleList = branchInfo.getAvailableVehicle()
                                                                     .get(vehicleType);
                Collections.sort(availableVehicleList);

                final Vehicle vehicle = availableVehicleList.get(0);
                availableVehicleList.remove(0);

                final BookingDetails bookingDetails = BookingDetails.builder()
                                                                    .bookingId(UUID.randomUUID()
                                                                                   .toString())
                                                                    .bookingAmount(bookingHours * vehicle.getRate())
                                                                    .build();

                final BookedVehicle bookedVehicle = BookedVehicle.builder()
                                                                 .bookingDetails(bookingDetails)
                                                                 .bookingHours(bookingHours)
                                                                 .startTime(TimeStampUtil.getTimeStamp())
                                                                 .vehicle(vehicle)
                                                                 .build();

                final Map<String, BookedVehicle> bookedVehicleMap = branchInfo.getBookedVehicle();
                bookedVehicleMap.put(bookingDetails.getBookingId(), bookedVehicle);
                branchInfo.setBookedVehicle(bookedVehicleMap);
                showBookingDetails(bookingDetails);
                return bookingDetails;
            } else {
                System.out.println("No vehicle available");
                return null;
            }
        } catch (RuntimeException ex) {
            System.out.println(ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }

    public Double returnVehicle(@NonNull final String branchId, @NonNull final String bookingId) {
        try {
            final BranchInfo branchInfo = BRANCH_LIST.getBranchInfoMap()
                                                     .get(branchId);
            final BookedVehicle bookedVehicle = branchInfo.getBookedVehicle()
                                                          .get(bookingId);
            bookedVehicle.setEndTime(TimeStampUtil.getTimeStamp());
            final Long bookingHours = bookedVehicle.getBookingHours();
            final Long hoursVehicleUsed = TimeStampUtil.getTimeInHour(bookedVehicle.getStartTime(),
                    bookedVehicle.getEndTime());

            addVehicleInAvailableList(branchInfo, bookedVehicle);
            if (bookingHours >= hoursVehicleUsed) {
                System.out.println("Please visit again");
                return 0.0;
            } else {
                final Double settlementAmount = (hoursVehicleUsed - bookingHours) * bookedVehicle.getVehicle()
                                                                                                 .getRate();
                System.out.printf("Since you used %d more hours than booking time, Please pay final remaining amount"
                        + " %.2f", (hoursVehicleUsed - bookingHours), settlementAmount);
                return settlementAmount;
            }
        } catch (RuntimeException ex) {
            System.out.println(ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }

    public void showBookingDetails(@NonNull final BookingDetails bookingDetails) {
        try {
            System.out.printf("Booking details : %s%n", bookingDetails);
        } catch (RuntimeException ex) {
            System.out.println(ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void addVehicleInAvailableList(final BranchInfo branchInfo, final BookedVehicle bookedVehicle) {
        branchInfo.getAvailableVehicle()
                  .get(bookedVehicle.getVehicle()
                                    .getVehicleType())
                  .add(bookedVehicle.getVehicle());

    }
}
