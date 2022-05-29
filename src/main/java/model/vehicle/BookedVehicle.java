package model.vehicle;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookedVehicle {
    private Long           startTime;
    private Long           endTime;
    private Long           bookingHours;
    private BookingDetails bookingDetails;
    private Vehicle        vehicle;
}
