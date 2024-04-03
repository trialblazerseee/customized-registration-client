package io.mosip.registration.api.signaturescanner;

import io.mosip.registration.api.docscanner.dto.DocScanDevice;
import io.mosip.registration.api.signaturescanner.constant.StreamType;
import io.mosip.registration.api.signaturescanner.dto.SignDevice;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.security.SignatureException;
import java.util.List;

public interface SignatureService {

    String getServiceName();

    void scan(SignDevice docScanDevice, String deviceType) throws Exception;

    void retry() throws Exception;

    void cancel() throws Exception;

    void confirm() throws Exception;

    byte[] loadData(StreamType streamType) throws SignatureException, IOException;

    List<SignDevice> getConnectedDevices() throws Exception;

    void stop(SignDevice docScanDevice);
}
