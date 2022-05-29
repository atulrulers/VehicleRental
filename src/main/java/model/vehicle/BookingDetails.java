package model.vehicle;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookingDetails {
    private String bookingId;
    private Double bookingAmount;
}
