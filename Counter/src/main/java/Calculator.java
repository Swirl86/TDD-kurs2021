import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    /*
    (?<=\d) means the previous character is a digit
    (?=\D) means the next character is a non-digit
    (?<=\d)(?=\D) together will match between a digit and a non-digit
    regexA|regexB means either regexA or regexB is matched,
    which is used as above points, but non-digit then digit for the visa-versa logic
    //String[] array = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    */

    public double breakDownString(String expression) throws CalculatorException {
        String[] operations = {"(", "*", "/", "-", "+"}; // Priority order

        List<String> expressions = parsExpression(expression);

        System.out.println(expressions);
        double result = 0.0;
        List<String> resultList = expressions;
        for (String operation : operations) {
            // Find all operation of x type e.g. calculate all * then all / etc.
            List<Integer> indices = Utils.getIndices(expressions, operation);

            if (operation.equals("(") && indices.size() != 0){
                for (int i = 0; i < indices.size(); i++) // Calculate every Parenthesis
                    resultList = calculateBetweenParenthesis(expressions, operation);
            } else resultList = calculateAllOfSameOperationType(expressions, operation, indices);
        }

        return Double.parseDouble(resultList.get(0));
    }

    private List<String> parsExpression(String expression) {
        return Pattern.compile("(\\d+|[(*/+\\-)]|[A-Za-z]+)")
                .matcher(expression)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
    }

    private List<String> calculateBetweenParenthesis(List<String> expression, String operation) throws CalculatorException {
        double result, firstNumber, secondNumber;
        String operationType;

        int startParen = Utils.getIndices(expression, "(").get(0);
        int endParen = Utils.getIndices(expression, ")").get(0);

            // Handle parentheses in parentheses
            for (int i = (startParen + 2); i <= (endParen - 2);
                 startParen = Utils.getIndices(expression, "(").get(0),
                         endParen = Utils.getIndices(expression, ")").get(0)) {

                operationType = expression.get(i);
                firstNumber = Utils.getNumberOrVariableValue(expression, i - 1);
                secondNumber = Utils.getNumberOrVariableValue(expression, i + 1);
                result = Utils.calculate(firstNumber, secondNumber, operationType);
                expression = Utils.handleCalculatedExpressionResult(expression, result, i);

                System.out.println(expression);
            }

        firstNumber = Utils.getNumberOrVariableValue(expression, startParen - 1);
        secondNumber = Utils.getNumberOrVariableValue(expression, startParen + 1);

        result = Utils.calculate(firstNumber, secondNumber, "*");

        expression.remove(startParen - 1);
        expression.remove(startParen);
        expression.remove(startParen);
        expression.remove(startParen - 1);
        expression.add(startParen - 1, Double.toString(result));
        System.out.println(expression);

        return expression;
    }

    private List<String> calculateAllOfSameOperationType(List<String> expression, String operation,
                                                         List<Integer> indices) throws CalculatorException {
        double result, first, second;

        for (int index : indices) {
            //String opValue = operation.equals("(") ? "*" : operation;

            first = Utils.getNumberOrVariableValue(expression, index - 1);
            second = Utils.getNumberOrVariableValue(expression, index + 1);

            result = Utils.calculate(first, second, operation);
            expression = Utils.handleCalculatedExpressionResult(expression, result, index);

            System.out.println(expression);
        }
        return expression;
    }
}