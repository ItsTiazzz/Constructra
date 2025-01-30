package org.tywrapstudios.constructra.api.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StringCalculatorTest {
    private static final Stack<String> CACHE = new Stack<>();

    @Test
    public void test1() {
        List<String> computed = ShuntingYard.execute(calc("( 1+ 4) * 5 * 4 "));
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test2() {
        List<String> computed = ShuntingYard.execute(calc("-1 * (4+5)"));
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test3() {
        List<String> computed = ShuntingYard.execute(calc("2 + 2a"));
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test4() {
        System.out.println("---------PF Calc---------");
        List<String> computed = ShuntingYard.execute(calc("(5 + 2) ^ 3"));
        System.out.println("postfix (computed): " + computed);
        StringCalculator.CalculationBuilder builder = new StringCalculator.CalculationBuilder();
        double d = builder.fromPostfix(computed).build();
        double e = (5 + 2) * (5 + 2) * (5 + 2);
        System.out.println("expected: " + e);
        System.out.println("result: " + d);
        System.out.println("---------PF Calc---------");
        Assertions.assertEquals(e, d);
    }

    @Test
    public void test5() {
        System.out.println("---------SC Calc---------");
        String calculation = "2 + sqrt 9 * 3";
        double d = StringCalculator.calculate(calculation);
        double e = 0;
        System.out.println("expected: " + e);
        System.out.println("result: " + d);
        System.out.println("---------SC Calc---------");
        Assertions.assertEquals(e, d);
    }

    private List<String> calc(String calc) {
        calc = calc
                .replaceAll(" ", "")
                .replaceAll(",", ".");
        StringCalculator.CalculationBuilder builder = new StringCalculator.CalculationBuilder();
        List<String> tokens = new ArrayList<>();

        for (char c : calc.toCharArray()) {
            String s = String.valueOf(c);
            if (s.matches("\\d|[.]")) {
                CACHE.push(s);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < CACHE.size(); i++) {
                    stringBuilder.append(CACHE.remove(i));
                }
                String finalizedCachedToken = stringBuilder.toString();
                if (!finalizedCachedToken.isEmpty()) tokens.add(finalizedCachedToken);
                tokens.add(s);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < CACHE.size(); i++) {
            stringBuilder.append(CACHE.remove(i));
        }
        String finalizedLastToken = stringBuilder.toString();
        if (!finalizedLastToken.isEmpty()) tokens.add(finalizedLastToken);

        System.out.println("Tokens: " + tokens);
        return tokens;
    }
}
