package com.bsoftgroup.springcloudmsbconsulta.controller;

import com.bsoftgroup.springcloudmsbconsulta.bean.BillingServiceBean;
import com.bsoftgroup.springcloudmsbconsulta.bean.ClientBean;
import com.bsoftgroup.springcloudmsbconsulta.interfaces.QueryBusFeignInterface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QueryBusRestController {

    //Deactivate if you use Eureka Server
//	@Autowired
//	private QueryBusProxyInterface queryBusProxy;

    @Autowired
    private QueryBusFeignInterface queryBusFeignInterface;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Use only with spring boot
//	// Get data of del MS Consulta by RestController
//	@GetMapping(path = "/billing")
//	public List<BillingServiceBean> getServices() {
//		return queryBusProxy.getServices();
//	}
//	
//	// Get data of MS Consulta by RestController
//	@GetMapping("/billing/idClient/{idClient}/idCompany/{idCompany}")
//	public List<BillingServiceBean> getServices(@PathVariable("idClient") Long idClient,
//			@PathVariable("idCompany") Long idCompany) {
//		return queryBusProxy.getServices(idClient, idCompany);
//	}

    // Get data of MS Consulta with Feign
    @GetMapping("/billing/feign")
    @HystrixCommand(fallbackMethod = "getServicesFeignHystrix")
    public List<BillingServiceBean> getServicesFeign() {
        logger.info("/billing/feign");
        return queryBusFeignInterface.getServices();
    }

    @GetMapping("/billing/feign/idClient/{idClient}/idCompany/{idCompany}")
    @HystrixCommand(fallbackMethod = "getServiceWithParamsFeignHystrix")
    public List<BillingServiceBean> getServicesFeign(@PathVariable("idClient") Long idClient,
                                                     @PathVariable("idCompany") Long idCompany) {
        logger.info("/billing/feign/idClient/{}/idCompany/{}", idClient, idCompany);
        return queryBusFeignInterface.getServices(idClient, idCompany);
    }

    public List<BillingServiceBean> getServicesFeignHystrix() {
        logger.info("/billing/feign");

        List<BillingServiceBean> list = new ArrayList<>();
        BillingServiceBean billingServiceBean = new BillingServiceBean(1L, null, new BigDecimal("20"), null, null);

        list.add(billingServiceBean);

        return list;
    }

    public List<BillingServiceBean> getServiceWithParamsFeignHystrix(@PathVariable("idClient") Long idClient,
                                                                     @PathVariable("idCompany") Long idCompany) {

        logger.info("/billing/feign/idClient/{}/idCompany/{}", idClient, idCompany);
        ArrayList<BillingServiceBean> list = new ArrayList<>();

        ClientBean clientBean = new ClientBean(idClient, null, null);
        BillingServiceBean billingServiceBean = new BillingServiceBean(1L, null, new BigDecimal("20"), clientBean, null);

        list.add(billingServiceBean);

        return list;
    }

}
