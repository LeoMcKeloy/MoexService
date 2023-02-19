package com.pyryanov.moexservice.parser;

import com.pyryanov.moexservice.dto.BondDto;
import java.util.List;

public interface Parser {
    List<BondDto> parse(String ratesAsString);
}
