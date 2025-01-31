package org.tywrapstudios.constructra.api.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.tywrapstudios.constructra.Constructra;

import java.util.ArrayList;
import java.util.List;

public class StringCalculatorTest {
    @BeforeAll
    static void setup() {
        Constructra.reloadConfig();
    }

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

    private List<String> calc(String calc) {
        try {
            return ShuntingYard.getInfix(calc);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
