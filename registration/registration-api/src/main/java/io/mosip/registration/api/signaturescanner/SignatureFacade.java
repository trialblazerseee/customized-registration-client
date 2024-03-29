package io.mosip.registration.api.signaturescanner;

import io.micrometer.core.annotation.Timed;
import io.mosip.registration.api.docscanner.DeviceType;
import io.mosip.registration.api.docscanner.DocScannerService;
import io.mosip.registration.api.docscanner.dto.DocScanDevice;
import io.mosip.registration.api.signaturescanner.dto.SignDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SignatureFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureFacade.class);

    @Value("#{${mosip.registration.signature.scanner.model}}")
    private Map<String, String> model;


    @Autowired
    private List<SignatureService> signatureServiceList;

    /**
     * Provides all the devices including both SCANNER and CAMERA devices
     * @return
     */
    public List<SignDevice> getConnectedDevices() {
        List<SignDevice> allDevices = new ArrayList<>();
        if(signatureServiceList == null || signatureServiceList.isEmpty()) {
            LOGGER.warn("** NO SIGNATURE SCANNER SERVICE IMPLEMENTATIONS FOUND!! **");
            return allDevices;
        }

        for(SignatureService service : signatureServiceList) {
            try {
                Objects.requireNonNull(service.getConnectedDevices()).forEach(device -> {
  //                      allDevices.add(setDefaults(device));
                    allDevices.add(device);
                });
            } catch (Throwable t) {
                LOGGER.error("Failed to get connected device list from service " + service.getServiceName(), t);
            }
        }
        return allDevices;
    }

    @Timed
    public BufferedImage scanDocument(@NonNull SignDevice docScanDevice, String deviceType) {
     //   setDefaults(docScanDevice);
        LOGGER.debug("Selected device details with configuration fully set : {}", docScanDevice);
        Optional<SignatureService> result = signatureServiceList.stream()
                .filter(s -> s.getServiceName().equals(docScanDevice.getModel())).findFirst();

        if(result.isPresent()) {
            return result.get().scan(docScanDevice, deviceType);
        }
        return null;
    }

  /*  private SignDevice setDefaults(@NonNull SignDevice docScanDevice) {
        if(model != null && docScanDevice.getModel() != null) {
            Optional<String> result = model.keySet().stream().filter( k -> docScanDevice.getModel().matches(model.get(k)) ).findFirst();
            if(result.isPresent()) {
                docScanDevice.setDpi(dpi.getOrDefault(result.get(),0));
                docScanDevice.setWidth(width.getOrDefault(result.get(),0));
                docScanDevice.setHeight(height.getOrDefault(result.get(),0));
            }
        }
        return docScanDevice;
    }*/

    public void stopDevice(@NonNull SignDevice docScanDevice) {
        try {
            if(signatureServiceList == null || signatureServiceList.isEmpty())
                return;

            Optional<SignatureService> result = signatureServiceList.stream()
                    .filter(s -> s.getServiceName().equals(docScanDevice.getModel())).findFirst();

            if(result.isPresent())
                result.get().stop(docScanDevice);
        } catch (Exception e) {
            LOGGER.error("Error while stopping device {}", docScanDevice.getModel(), e);
        }
    }
}
