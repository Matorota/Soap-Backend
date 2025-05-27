package lt.viko.eif.mstrimaitis.service;

import lt.viko.eif.mstrimaitis.model.GetFarmerRequest;
import lt.viko.eif.mstrimaitis.model.GetFarmerResponse;
import lt.viko.eif.mstrimaitis.db.FarmerRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FarmerEndPoint {
    private static final String NAMESPACE_URI = "http://viko.eif.lt/farmers";
    private final FarmerRepository farmerRepository;

    public FarmerEndPoint(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFarmerRequest")
    @ResponsePayload
    public GetFarmerResponse getFarmer(@RequestPayload GetFarmerRequest request) {
        GetFarmerResponse response = new GetFarmerResponse();
        farmerRepository.findByName(request.getName()).ifPresent(response::setFarmer);
        return response;
    }
}