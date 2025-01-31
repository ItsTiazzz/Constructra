package org.tywrapstudios.constructra.api.calculation;

import java.util.*;

import static java.lang.Double.NaN;
import static org.tywrapstudios.constructra.Constructra.LOGGER;

public class StringCalculator {
    public static double calculate(String calculation) {
        CalculationBuilder builder = new CalculationBuilder();

        try {
            List<String> tokens = ShuntingYard.getInfix(calculation);
            LOGGER.debug("[StringCalculator] Tokens: " + tokens);
            List<String> postfixTokens = ShuntingYard.execute(tokens);
            LOGGER.debug("[StringCalculator] Postfix: " + postfixTokens);

            return builder.fromPostfix(postfixTokens).build();
        } catch (InvalidCalculationException e) {
            LOGGER.error("[StringCalculator] Invalid Calculation: Could not get proper Infix Tokens from String: " + calculation);
            e.printStackTrace();
            return NaN;
        } catch (Exception e) {
            LOGGER.error("[StringCalculator] An unexpected Exception occurred while getting Infix Tokens for " + calculation);
            e.printStackTrace();
            return NaN;
        }
    }

    public static class CalculationBuilder {
        private static String calcString;
        private static StringBuilder calcStringBuilder;
        private static double calcOutCome;
        private final static Map<String, Operator> OPS = new HashMap<>();

        public CalculationBuilder() {
            calcStringBuilder = new StringBuilder();
            calcOutCome = 0.0;
        }

        public CalculationBuilder fromPostfix(List<String> postfix) {
            Stack<String> N = new Stack<>();
            for (String s : postfix) {
                LOGGER.debug("[StringCalculator$CalculationBuilder] Checking Token: " + s);
                if (!OPS.containsKey(s)) {
                    N.push(s);
                    LOGGER.debug("[StringCalculator$CalculationBuilder] Pushed: " + s);
                } else {
                    Operator op = OPS.get(s);
                    double right = Double.parseDouble(N.pop());
                    double left = Double.parseDouble(N.pop());
                    double newD = getFromOperation(op, left, right);
                    LOGGER.debug("[StringCalculator$CalculationBuilder] newD: " + newD);
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
                LOGGER.debug("[StringCalculator$CalculationBuilder] Addition completed: " + calcOutCome + " " + calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder subtract(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome - d;
                calcStringBuilder.append(String.format(" - %s", d));
                LOGGER.debug("[StringCalculator$CalculationBuilder] Subtraction completed: " + calcOutCome + " " + calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder multiply(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome * d;
                calcStringBuilder.append(String.format(" * %s", d));
                LOGGER.debug("[StringCalculator$CalculationBuilder] Multiplication completed: " + calcOutCome + " " + calcStringBuilder.toString());
            }
            return this;
        }

        public CalculationBuilder divide(double... d1) {
            for (double d : d1) {
                calcOutCome = calcOutCome / d;
                calcStringBuilder.append(String.format(" / %s", d));
                LOGGER.debug("[StringCalculator$CalculationBuilder] Division completed: " + calcOutCome + " " + calcStringBuilder.toString());
            }
            return this;
        }

        public double build() {
            calcString = calcStringBuilder.toString();
            LOGGER.debug("[StringCalculator$CalculationBuilder] Finalized Builder with final outcome of: " + calcOutCome);
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
