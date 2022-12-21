package com.bsoftgroup.springcloudmsbconsulta.interfaces;

import com.bsoftgroup.springcloudmsbconsulta.bean.BillingServiceBean;

import java.util.List;

public interface QueryBusProxyInterface {

    public List<BillingServiceBean> getServices();

    public List<BillingServiceBean> getServices(Long idClient, Long idCompany);

}
