package io.mosip.registration.ref.signotec;

import io.mosip.registration.api.signaturescanner.SignatureService;
import io.mosip.registration.api.signaturescanner.constant.StreamType;
import io.mosip.registration.api.signaturescanner.dto.SignDevice;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SignatureException;
import java.util.List;


@Component
public class SignotecScanServiceImpl implements SignatureService {

    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public void scan(SignDevice docScanDevice, String deviceType) throws Exception {

    }

    @Override
    public void retry() throws Exception {

    }

    @Override
    public void cancel() throws Exception {

    }

    @Override
    public void confirm() throws Exception {

    }

    @Override
    public byte[] LoadImage(StreamType streamType) throws SignatureException, IOException {
        return new byte[0];
    }

    @Override
    public List<SignDevice> getConnectedDevices() throws Exception {
        return null;
    }

    @Override
    public void stop(SignDevice docScanDevice) {

    }
}
