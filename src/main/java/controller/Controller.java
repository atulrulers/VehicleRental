package controller;

import lombok.extern.log4j.Log4j2;
import model.vehicle.BookingDetails;
import model.vehicle.VehicleType;
import service.BookingService;
import service.BranchService;
import service.VehicleService;

import java.util.ArrayList;
import java.util.List;

import static model.vehicle.VehicleType.BIKE;
import static model.vehicle.VehicleType.BUS;
import static model.vehicle.VehicleType.CAR;
import static model.vehicle.VehicleType.SCOOTY;
import static model.vehicle.VehicleType.VAN;

@Log4j2
public class Controller {

    public static void main(String... args) {
        final BranchService branchService = new BranchService();
        final VehicleService vehicleService = new VehicleService();
        final BookingService bookingService = new BookingService();

        final List<VehicleType> commonVehicle = List.of(CAR, BIKE, VAN);
        final List<VehicleType> vehicleTypeList = new ArrayList<>(commonVehicle);
        branchService.addBranch("B1", vehicleTypeList);
        branchService.showAllBranches();

        vehicleTypeList.add(SCOOTY);
        branchService.addBranch("B2", vehicleTypeList);
        branchService.showAllBranches();

        // Adding vehicle
        vehicleService.addVehicleInBranch("B1", CAR, "V1", 500.00);
        vehicleService.addVehicleInBranch("B1", CAR, "V2", 1000.00);
        vehicleService.addVehicleInBranch("B1", BIKE, "V3", 250.00);
        vehicleService.addVehicleInBranch("B1", BIKE, "V4", 300.00);
        vehicleService.addVehicleInBranch("B1", BUS, "V5", 2500.00);

        vehicleService.showAvailableVehicleInBranch("B1");

        // booking vehicle
        BookingDetails b1 = bookingService.bookVehicle("B1", CAR, 3L);
        BookingDetails b2 = bookingService.bookVehicle("B1", CAR, 5L);
        BookingDetails b3 = bookingService.bookVehicle("B1", CAR, 5L); // showing no cars available
        branchService.showBranchDetails("B1");

        // return vehicle
        bookingService.returnVehicle("B1", b2.getBookingId());
        b2 = bookingService.bookVehicle("B1", CAR, 5L);
        bookingService.returnVehicle("B1", b1.getBookingId());
        bookingService.bookVehicle("B1", CAR, 0L);
        bookingService.bookVehicle("B1", CAR, 5L);
        bookingService.returnVehicle("B1", b2.getBookingId());
        bookingService.bookVehicle("B1", CAR, 5L);

    }
}
