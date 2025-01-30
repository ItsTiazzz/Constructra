package org.tywrapstudios.constructra.api.calculation;

import java.util.*;

import static org.tywrapstudios.constructra.api.calculation.Associativity.LEFT;
import static org.tywrapstudios.constructra.api.calculation.Associativity.RIGHT;

public class ShuntingYard {
    final static Map<String, Operator> OPS = new HashMap<>();

    static {
        // We build a map with all the existing Operators by iterating over the existing Enum
        // and filling up the map with:
        // <K,V> = <Character, Operator(Character, Associativity, Precedence)>
        for (Operator operator : Operator.values()) {
            OPS.put(operator.symbol, operator);
        }
    }

    public static List<String> execute(List<String> tokens) {
        List<String> output = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        // For all the input tokens read the next token
        for (String token : tokens) {
            if (OPS.containsKey(token)) {
                // Token is an operator
                while (!stack.isEmpty() && OPS.containsKey(stack.peek())) {
                    // While there is an operator (y) at the top of the operators stack and
                    // either (x) is left-associative and its precedence is less or equal to
                    // that of (y), or (x) is right-associative and its precedence
                    // is less than (y)
                    Operator cOp = OPS.get(token); // Current operator
                    Operator lOp = OPS.get(stack.peek()); // Top operator from the stack
                    if ((cOp.associativity == LEFT && cOp.comparePrecedence(lOp) <= 0) ||
                            (cOp.associativity == RIGHT && cOp.comparePrecedence(lOp) < 0)) {
                        // Pop (y) from the stack
                        // Add (y) output buffer
                        output.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);
            } else if ("(".equals(token)) {
                // Else If token is left parenthesis, then push it on the stack
                stack.push(token);
            } else if (")".equals(token)) {
                // Else If the token is right parenthesis
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    // Until the top token (from the stack) is left parenthesis, pop from
                    // the stack to the output buffer
                    output.add(stack.pop());
                }
                // Also pop the left parenthesis but don't include it in the output buffer
                stack.pop();
            } else {
                // Else add token to output buffer
                output.add(token);
            }
        }

        while (!stack.isEmpty()) {
            // While there are still operator tokens in the stack, pop them to output S[13]
            output.add(stack.pop());
        }

        return output;
    }
}
