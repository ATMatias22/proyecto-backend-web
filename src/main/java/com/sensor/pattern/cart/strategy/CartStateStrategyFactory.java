package com.sensor.pattern.cart.strategy;

import com.sensor.enums.CartState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartStateStrategyFactory {
    private final Map<CartState, CartStateStrategy> strategies;

    public CartStateStrategyFactory(List<CartStateStrategy> strategyList) {
        this.strategies = new HashMap<>();
        for (CartStateStrategy strategy : strategyList) {
            strategies.put(strategy.getState(), strategy);
        }
    }

    public CartStateStrategy getStrategy(CartState state) {
        return strategies.get(state);
    }

}
