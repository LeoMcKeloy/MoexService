package com.pyryanov.moexservice.dto;

import com.pyryanov.moexservice.model.Stock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class StocksDto {
    List<Stock> stocks;
}
