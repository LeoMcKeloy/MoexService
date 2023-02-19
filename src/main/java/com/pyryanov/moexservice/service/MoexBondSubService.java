package com.pyryanov.moexservice.service;

import com.pyryanov.moexservice.client.CorporateBondsClient;
import com.pyryanov.moexservice.client.GovBondsClient;
import com.pyryanov.moexservice.dto.BondDto;
import com.pyryanov.moexservice.exception.LimitRequestsException;
import com.pyryanov.moexservice.parser.Parser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MoexBondSubService implements BondSubService {

    private final CorporateBondsClient corporateBondsClient;
    private final GovBondsClient govBondsClient;
    private final Parser parser;

    @Cacheable(value = "corps")
    @Override
    public List<BondDto> getCorporateBonds() {
        log.info("Getting corporate bonds from Moex");
        String xmlFromMoex = corporateBondsClient.getBondsFromMoex();
        List<BondDto> bondDtos = parser.parse(xmlFromMoex);
        if (bondDtos.isEmpty()) {
            log.error("Moex isn't answering for getting corporate bonds");
            throw new LimitRequestsException("Moex isn't answering for getting corporate bonds");
        }
        return bondDtos;
    }

    @Cacheable(value = "govs")
    @Override
    public List<BondDto> getGovBonds() {
        log.info("Getting government bonds from Moex");
        String xmlFromMoex = govBondsClient.getBondsFromMoex();
        List<BondDto> bondDtos = parser.parse(xmlFromMoex);
        if (bondDtos.isEmpty()) {
            log.error("Moex isn't answering for getting government bonds");
            throw new LimitRequestsException("Moex isn't answering for getting government bonds");
        }
        return bondDtos;
    }
}
