package org.tywrapstudios.constructra.api.calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringCalculator.class);
    private static final Pattern NON_DIGIT_OR_OPERATORS = Pattern.compile("");
    private static final Stack<String> CACHE = new Stack<>();

    public static double calculate(String calc) {
        calc = calc
                .replaceAll(" ", "")    // Ensure everything is next to each other
                .replaceAll(",", ".");  // Ensure there are no "," decimal points, as Java will only recognise "."

        CalculationBuilder builder = new CalculationBuilder();
        List<String> tokens = new ArrayList<>();

        LOGGER.info("Checking calc: {}", calc);

        for (char c : calc.toCharArray()) {
            String s = String.valueOf(c);
            if (s.matches("\\d|[.]")) {
                CACHE.push(s);
                LOGGER.info("Pushed to cache: {}", s);
            } else if (s.matches("[-+*/%^]")) {
                StringBuilder cachedTokenBuilder = new StringBuilder();
                for (int i = 0; i < CACHE.size() + i; i++) {
                    cachedTokenBuilder.append(CACHE.removeFirst());
                }
                String finalizedCachedToken = cachedTokenBuilder.toString();
                if (!finalizedCachedToken.isEmpty()) tokens.add(finalizedCachedToken);
                LOGGER.info("finalizedCachedToken: {}", finalizedCachedToken);
                tokens.add(s);
                LOGGER.info("Add to tokens: {}", s);
            } else {
                CACHE.clear();
                return 0;
            }
        }

        StringBuilder lastTokenBuilder = new StringBuilder();
        for (int i = 0; i < CACHE.size() + i; i++) {
            lastTokenBuilder.append(CACHE.removeFirst());
        }
        String finalizedLastToken = lastTokenBuilder.toString();
        if (!finalizedLastToken.isEmpty()) tokens.add(finalizedLastToken);

        LOGGER.info("Tokens: {}", tokens);
        List<String> postfixTokens = ShuntingYard.execute(tokens);
        LOGGER.info("Postfix: {}", postfixTokens);

        return builder.fromPostfix(postfixTokens).build();
    }

    public static class CalculationBuilder {
        private static String calcString;
        private static StringBuilder calcStringBuilder;
        private static double calcOutCome;
        private final static Map<String, Operator> OPS = new HashMap<>();
        private static final Logger LOGGER = LoggerFactory.getLogger(CalculationBuilder.class);

        public CalculationBuilder() {
            calcStringBuilder = new StringBuilder();
            calcOutCome = 0.0;
        }

        public CalculationBuilder fromPostfix(List<String> postfix) {
//            postfix.addFirst("0");
//            postfix.addLast("+");
            Stack<String> N = new Stack<>();
            for (String s : postfix) {
                LOGGER.info("Checking Token: {}", s);
                if (!OPS.containsKey(s)) {
                    N.push(s);
                    LOGGER.info("Pushed: {}", s);
                } else {
                    Operator op = OPS.get(s);
                    double right = Double.parseDouble(N.pop());
                    double left = Double.parseDouble(N.pop());
                    double newD = getFromOperation(op, left, right);
                    LOGGER.info("newD: {}", newD);
                    N.push(String.valueOf(newD));
                }
            }
            add(Double.parseDouble(N.pop()));
            return this;
        }

        private double getFromOperation(Operator op, double d1, double d2) {
            return switch (op) {
                case ADDITION -> d1 + d2;
                case SUBTRACTION -> d1 - d2;
                case MULTIPLICATION -> d1 * d2;
                case DIVISION -> d1 / d2;
                case MODULUS -> d1 % d2;
                case POWER -> Math.pow(d1, d2);
            };
        }

        public CalculationBuilder doOperation(Operator op, double d1) {
            return switch (op) {
                case ADDITION -> this.add(d1);
                case SUBTRACTION -> this.subtract(d1);
                case MULTIPLICATION -> this.multiply(d1);
                case DIVISION -> this.divide(d1);
                default -> this;
            };
        }

        public CalculationBuilder add(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome + d;
                calcStringBuilder.append(String.format(" + %s", d));
                LOGGER.info("Addition completed: {} {}", calcOutCome, calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder subtract(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome - d;
                calcStringBuilder.append(String.format(" - %s", d));
                LOGGER.info("Subtraction completed: {} {}", calcOutCome, calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder multiply(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome * d;
                calcStringBuilder.append(String.format(" * %s", d));
                LOGGER.info("Multiplication completed: {} {}", calcOutCome, calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder divide(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome / d;
                calcStringBuilder.append(String.format(" / %s", d));
                LOGGER.info("Divide completed: {} {}", calcOutCome, calcStringBuilder.toString());
            }
            return this;
        }

        public double build() {
            calcString = calcStringBuilder.toString();
            LOGGER.info("Finalized Builder with final outcome of: {}", calcOutCome);
            return calcOutCome;
        }

        public String getCalculationString() {
            return calcString;
        }

        static {
            for (Operator operator : Operator.values()) {
                OPS.put(operator.symbol, operator);
            }
        }
    }
}
