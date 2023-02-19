package com.pyryanov.moexservice.client;

import com.pyryanov.moexservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "corporatebonds", url = "${moex.bonds.corporate.url}", configuration = FeignConfig.class)
public interface CorporateBondsClient {
    @GetMapping
    String getBondsFromMoex();
}
