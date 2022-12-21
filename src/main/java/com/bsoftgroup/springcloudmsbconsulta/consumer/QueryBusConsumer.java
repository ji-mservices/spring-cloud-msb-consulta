package com.bsoftgroup.springcloudmsbconsulta.consumer;

import com.bsoftgroup.springcloudmsbconsulta.bean.BillingServiceBean;
import com.bsoftgroup.springcloudmsbconsulta.interfaces.QueryBusProxyInterface;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QueryBusConsumer implements QueryBusProxyInterface {

    private static final String URL_QUERY = "http://localhost:8081/msconsulta/billing";

    private static final String URL_BILLING = "http://localhost:8081/msconsulta/billing/idClient/{idClient}/idCompany/{idCompany}";

    @Override
    public List<BillingServiceBean> getServices() {
        List<BillingServiceBean> services = new ArrayList<>();

        ResponseEntity<List<BillingServiceBean>> responseEntity = new RestTemplate().exchange(URL_QUERY, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        }, new HashMap<>());
        if (responseEntity.hasBody()) {
            services = responseEntity.getBody();
        }
        return services;
    }

    @Override
    public List<BillingServiceBean> getServices(Long idClient, Long idCompany) {
        List<BillingServiceBean> services = new ArrayList<>();
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("idClient", idClient);
        uriVariables.put("idCompany", idCompany);
        ResponseEntity<List<BillingServiceBean>> responseEntity = new RestTemplate().exchange(URL_BILLING, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        }, uriVariables);
        if (responseEntity.hasBody()) {
            services = responseEntity.getBody();
        }
        return services;
    }

}
