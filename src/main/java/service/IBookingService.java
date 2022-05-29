package service;

import lombok.NonNull;
import model.vehicle.BookingDetails;
import model.vehicle.VehicleType;

public interface IBookingService {
    public BookingDetails bookVehicle(@NonNull final String branchId, @NonNull final VehicleType vehicleType,
            @NonNull final Long bookingHours);

    public Double returnVehicle(@NonNull final String branchId, @NonNull final String bookingId);

    public void showBookingDetails(@NonNull final BookingDetails bookingDetails);
}
