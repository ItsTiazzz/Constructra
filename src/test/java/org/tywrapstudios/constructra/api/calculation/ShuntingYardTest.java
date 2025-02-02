package org.tywrapstudios.constructra.api.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.tywrapstudios.constructra.Constructra;

import java.util.Arrays;
import java.util.List;

public class ShuntingYardTest {
    @BeforeAll
    static void setup() {
        Constructra.reloadConfig();
    }

    @Test
    public void test1() {

        List<String> given = Arrays.asList("( 6 + 2 ) * ( 9 / 3 ) ^ ( 5 + 6 )".split(" "));
        List<String> expected = List.of("6", "2", "+", "9", "3", "/", "5", "6", "+", "^", "*");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test2() {
        List<String> given = Arrays.asList("( 5 + 2 ) ^ ( 5 + 7 )".split(" "));
        List<String> expected = List.of("5", "2", "+", "5", "7", "+", "^");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test3() {
        List<String> given = Arrays.asList("4 * 6 + 3 * 4".split(" "));
        List<String> expected = List.of("4", "6", "*", "3", "4", "*", "+");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test4() {
        List<String> given = Arrays.asList("4 * 6 + 3 * 4 + 10".split(" "));
        List<String> expected = List.of("4", "6", "*", "3", "4", "*", "+", "10", "+");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test5() {
        List<String> given = Arrays.asList("( 4 + 6 ) * 3 - 6 * ( 5 - 3 ) + 16 - 1".split(" "));
        List<String> expected = List.of("4", "6", "+", "3", "*", "6", "5", "3", "-", "*", "-", "16", "+", "1", "-");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test6() {
        List<String> given = Arrays.asList("- 1 + 6 * 3 * ( - 1 - 3 )".split(" "));
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + "No Expectation");
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test7() {
        List<String> given = Arrays.asList("2 ^ 2".split(" "));
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + "No Expectation");
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test8() {
        List<String> given = Arrays.asList("1 + 3 - 4 / 5 * ( 1 + 5 )".split(" "));
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + "No Expectation");
        System.out.println("postfix (computed): " + computed);
    }

    @Test
    public void test9() {
        List<String> given = Arrays.asList("- 6 + 7 * ( - 6 + 12 )".split(" "));
        List<String> expected = List.of("0", "6", "-", "7", "0", "6", "-", "12", "+", "*", "+");
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): " + expected);
        System.out.println("postfix (computed): " + computed);

        Assertions.assertEquals(expected, computed);
    }

    @Test
    public void test10() {
        List<String> given = Arrays.asList("1 + 3 - 10 / 5 * ( 1 + 5 )".split(" "));
        List<String> computed = ShuntingYard.execute(given);

        System.out.println("infix: " + given);
        System.out.println("postfix (expected): Nothing");
        System.out.println("postfix (computed): " + computed);
    }
}
