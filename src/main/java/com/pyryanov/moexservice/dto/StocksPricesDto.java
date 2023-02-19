package com.pyryanov.moexservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class StocksPricesDto {
    List<StockPrice> prices;
}
