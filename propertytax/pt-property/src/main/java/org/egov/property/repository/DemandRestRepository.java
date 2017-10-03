package org.egov.property.repository;

import lombok.extern.slf4j.Slf4j;
import org.egov.models.DemandRequest;
import org.egov.models.DemandResponse;
import org.egov.property.exception.DemandUpdateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DemandRestRepository {

    private final RestTemplate restTemplate;

    private final String url;

    public DemandRestRepository(RestTemplate restTemplate,
                                @Value("${egov.services.billing_service.hostname}") final String billingServiceHost,
                                @Value("${egov.services.billing_service.updatedemand}") final String billingServiceUrl) {
        this.restTemplate = restTemplate;
        this.url = billingServiceHost + billingServiceUrl;
    }

    public DemandResponse updateDemand(DemandRequest demandRequest){
        DemandResponse demandResponse = DemandResponse.builder().build();
        try{
            demandResponse = restTemplate.postForObject(url, demandRequest, DemandResponse.class);
        }
        catch(HttpStatusCodeException ex){
            if(ex.getStatusCode().equals(HttpStatus.BAD_REQUEST))
                throw new DemandUpdateException(ex);
        }
        catch (Exception e) {
            log.error("Following Exception Occurred While Calling User Service : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return  demandResponse;
    }
}
