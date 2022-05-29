package model.vehicle;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Vehicle implements Comparable<Vehicle> {
    private String      registrationNumber;
    private String      vehicleModel;
    private String      brandName;
    private Double      rate;
    private VehicleType vehicleType;

    @Override
    public int compareTo(Vehicle v) {
        return this.rate.compareTo(v.getRate());
    }

}
