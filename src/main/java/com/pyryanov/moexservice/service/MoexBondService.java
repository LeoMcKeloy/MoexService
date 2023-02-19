package com.pyryanov.moexservice.service;

import com.pyryanov.moexservice.client.CorporateBondsClient;
import com.pyryanov.moexservice.client.GovBondsClient;
import com.pyryanov.moexservice.dto.BondDto;
import com.pyryanov.moexservice.dto.FigiesDto;
import com.pyryanov.moexservice.dto.StockPrice;
import com.pyryanov.moexservice.dto.StocksDto;
import com.pyryanov.moexservice.dto.StocksPricesDto;
import com.pyryanov.moexservice.dto.TickersDto;
import com.pyryanov.moexservice.exception.BondNotFoundException;
import com.pyryanov.moexservice.exception.LimitRequestsException;
import com.pyryanov.moexservice.model.Currency;
import com.pyryanov.moexservice.model.Stock;
import com.pyryanov.moexservice.parser.Parser;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoexBondService implements BondService {

    private final MoexBondSubService subService;
    private final CacheManager cacheManager;

    @Override
    public StocksDto getBondsFromMoex(TickersDto tickersDto) {
        log.info("Request for tickers {}", tickersDto.getTickers());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(subService.getCorporateBonds());
        allBonds.addAll(subService.getGovBonds());
        List<BondDto> resultBonds = allBonds.stream()
                .filter(b -> tickersDto.getTickers().contains(b.getTicker()))
                .collect(Collectors.toList());
        List<Stock> stocks = resultBonds.stream().map(b -> Stock.builder()
                .ticker(b.getTicker())
                .figi(b.getTicker())
                .name(b.getName())
                .currency(Currency.RUB)
                .source("MOEX")
                .type("bond")
                .build()).collect(Collectors.toList());
        return new StocksDto(stocks);
    }

    @Override
    public StocksPricesDto getPricesByFigies(FigiesDto figiesDto) {
        log.info("Request for figies {}", figiesDto.getFigies());
        List<String> figies = new ArrayList<>(figiesDto.getFigies());
        List<BondDto> allBonds = new ArrayList<>();
        allBonds.addAll(subService.getCorporateBonds());
        allBonds.addAll(subService.getGovBonds());

        figies.removeAll(allBonds.stream().map(BondDto::getTicker).collect(Collectors.toList()));
        if (!figies.isEmpty()) {
            throw new BondNotFoundException(String.format("Bond %s not found", figies));
        }

        List<StockPrice> prices = allBonds.stream()
                .filter(b -> figiesDto.getFigies().contains(b.getTicker()))
                .map(b -> new StockPrice(b.getTicker(), b.getPrice() * 10))
                .collect(Collectors.toList());

        return new StocksPricesDto(prices);
    }
}
