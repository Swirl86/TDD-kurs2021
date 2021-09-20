import java.util.*;
import java.util.stream.IntStream;

interface Utils {
    ArrayList<String> numbersArray = new ArrayList<>(
            Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    );

    static double add(double first, double second) {
        return first + second;
    }

    static double subtract(double first, double second) {
        return first - second;
    }

    static double multiplication(double first, double second) {
        return first * second;
    }

    static double division(double first, double second) {
        return first / second;
    }

    static double calculate(double first, double second, String type) throws CalculatorException {
        System.out.println(first + "  " + type + "  " + second);
        return switch (type) {
            case "+" -> add(first, second);
            case "-" -> subtract(first, second);
            case "*" -> multiplication(first, second);
            case "/" -> division(first, second);
            default -> throw new CalculatorException("Illegal Argument");
        };
    }

    static List<Integer> getIndices(List<String> expression, String operation) {
        List<Integer> indices = IntStream.range(0, expression.size())
                //Get index for operation e.g.  "+", "add", "plus" -> "+"
                .filter(i -> Operations.getOperation(expression.get(i)).equals(operation))
                .collect(ArrayList::new, List::add, List::addAll);
        if(operation.equals("(") || operation.equals("*")|| operation.equals("/")){
            Collections.reverse(indices); // Start with the highest index
        }
        return indices;
    }

    static double getNumberOrVariableValue(List<String> expression, int index) {
        return expression.get(index).matches("[a-zA-z]+") ?
                Double.parseDouble(String.valueOf(numbersArray.indexOf(expression.get(index)))) :
                Double.parseDouble(expression.get(index));
    }

    static List<String> handleCalculatedExpressionResult(List<String> expression, double result, int i) {
        expression.remove(i + 1);
        expression.remove(i);
        expression.add(i - 1, Double.toString(result));
        expression.remove(i);
        return expression;
    }
}