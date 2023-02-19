package com.pyryanov.moexservice.service;

import com.pyryanov.moexservice.dto.FigiesDto;
import com.pyryanov.moexservice.dto.StocksDto;
import com.pyryanov.moexservice.dto.StocksPricesDto;
import com.pyryanov.moexservice.dto.TickersDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface BondService {
    StocksDto getBondsFromMoex(@RequestBody TickersDto tickersDto);
    StocksPricesDto getPricesByFigies(@RequestBody FigiesDto figiesDto);
}
