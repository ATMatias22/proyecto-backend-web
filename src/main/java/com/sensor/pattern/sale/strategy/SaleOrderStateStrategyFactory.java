package com.sensor.pattern.sale.strategy;

import com.sensor.enums.SaleOrderState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SaleOrderStateStrategyFactory {
    private final Map<SaleOrderState, SaleOrderStateStrategy> strategies;

    public SaleOrderStateStrategyFactory(List<SaleOrderStateStrategy> strategyList) {
        this.strategies = new HashMap<>();
        for (SaleOrderStateStrategy strategy : strategyList) {
            strategies.put(strategy.getState(), strategy);
        }
    }

    public SaleOrderStateStrategy getStrategy(SaleOrderState state) {
        return strategies.get(state);
    }

}
