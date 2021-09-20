interface Operations {

    static String getOperation(String operation) {
        return switch (operation) {
            case "(" -> "(";
            case "+", "add", "plus" -> "+";
            case "-", "minus", "sub" -> "-";
            case "*", "multiplied", "times" -> "*";
            case "/", "divided", "div" -> "/";
            case ")" -> ")";
            default -> "no";//throw new RuntimeException("Internal error " + operation);
        };
    }
}