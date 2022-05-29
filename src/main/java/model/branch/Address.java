package model.branch;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String fullAddress;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
