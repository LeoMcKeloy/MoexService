package com.pyryanov.moexservice.service;

import com.pyryanov.moexservice.dto.BondDto;
import java.util.List;

public interface BondSubService {
    List<BondDto> getCorporateBonds();
    List<BondDto> getGovBonds();
}
