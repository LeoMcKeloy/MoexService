package com.pyryanov.moexservice.controller;

import com.pyryanov.moexservice.dto.FigiesDto;
import com.pyryanov.moexservice.dto.StocksDto;
import com.pyryanov.moexservice.dto.StocksPricesDto;
import com.pyryanov.moexservice.dto.TickersDto;
import com.pyryanov.moexservice.service.BondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MoexBondController {

    private final BondService bondService;

    @PostMapping("/bonds")
    public StocksDto getBondsFromMoex(@RequestBody TickersDto tickersDto) {
        return bondService.getBondsFromMoex(tickersDto);
    }

    @PostMapping("/prices")
    public StocksPricesDto getPricesByFigies(@RequestBody FigiesDto figiesDto) {
        return bondService.getPricesByFigies(figiesDto);
    }
}