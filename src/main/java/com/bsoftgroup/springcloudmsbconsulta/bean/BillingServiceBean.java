package com.bsoftgroup.springcloudmsbconsulta.bean;

import java.math.BigDecimal;

public record BillingServiceBean(Long id, String status, BigDecimal amount, ClientBean client, ProductBean product) {

}
