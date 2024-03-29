package io.mosip.registration.api.signaturescanner;

import io.mosip.registration.api.docscanner.dto.DocScanDevice;
import io.mosip.registration.api.signaturescanner.dto.SignDevice;

import java.awt.image.BufferedImage;
import java.util.List;

public interface SignatureService {

    String getServiceName();

    BufferedImage scan(SignDevice docScanDevice, String deviceType);

    List<SignDevice> getConnectedDevices();

    void stop(SignDevice docScanDevice);
}
