package com.bsoftgroup.springcloudmsbconsulta.interfaces;

import com.bsoftgroup.springcloudmsbconsulta.bean.BillingServiceBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//1st way: Feign without Microservices
//@FeignClient(name="ms-consulta" , url="localhost:8081")  //Without using Eureka Server

//2nd way: Using Eureka Server, but without Api Gateway
//@FeignClient(name = "ms-consulta")

//3rd using Eureka Server and Api Gateway - (use ms-consulta in the application path)
@FeignClient(name="apigateway")
@RibbonClient(name = "ms-consulta")
public interface QueryBusFeignInterface {

//	@GetMapping(path = "/msconsulta/billing")				//Without Api Gateway
	@GetMapping(path = "/ms-consulta/msconsulta/billing")	//With Api Gateway
	public List<BillingServiceBean> getServices();

//	@GetMapping("/msconsulta/billing/idClient/{idClient}/idCompany/{idCompany}")				//Without Api Gateway
	@GetMapping("/ms-consulta/msconsulta/billing/idClient/{idClient}/idCompany/{idCompany}")	//With Api Gateway
	public List<BillingServiceBean> getServices(@PathVariable("idClient") Long idClient,
			@PathVariable("idCompany") Long idCompany);

}
