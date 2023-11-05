package com.tenpo.challenge.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomGenerator {

    private final Random random = new Random();

    public boolean getRandomBoolean(double probability) {
        return random.nextDouble() < probability;
    }

}
