package io.mosip.registration.api.signaturescanner.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SignDevice {
    private String model;
    private String firmware;
    private String serial;
    private String display;
    private String deviceId;
}
