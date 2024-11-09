public class CalculatorController {
    private double firstOperand = 0.0;
    private String operator = "";
    private boolean operatorClicked = false;
    private boolean calculationDone = false;

    public String processNumberInput(String currentText, String number) {
        if (calculationDone) {
            calculationDone = false;
            return number;
        }
        return operatorClicked && currentText.split(" ").length > 2 ? currentText : currentText + number;
    }

    public String processOperatorInput(String currentText, String op) {
        if (calculationDone) {
            firstOperand = Double.parseDouble(currentText.trim());
            calculationDone = false;
        } else if (!operatorClicked) {
            firstOperand = Double.parseDouble(currentText.trim());
            operatorClicked = true;
        }
        operator = op;

        if (op.equals("1/x") || op.equals("x^2") || op.equals("sqrt") || op.equals("+/-") ) {
            return executeSpecialCalculation(op);
        }
        return currentText + " " + op + " ";
    }

    public String executeCalculation(String currentText) {
        String[] operands = currentText.split(" ");


        double secondOperand = Double.parseDouble(operands[2]);
        double result = 0;

        switch (operator) {
            case "+":
                result = Calculator.add(firstOperand, secondOperand);
                break;
            case "-":
                result = Calculator.subtract(firstOperand, secondOperand);
                break;
            case "*":
                result = Calculator.multiply(firstOperand, secondOperand);
                break;
            case "/":
                result = Calculator.divide(firstOperand, secondOperand);
                break;
        }
        calculationDone = true;
        operatorClicked = false;
        return (result == Math.floor(result)) ? String.valueOf((int) result) : String.valueOf(result);
    }

    private String executeSpecialCalculation(String op) {
        double result = 0;
        switch (op) {
            case "1/x":
                result = Calculator.inverse(firstOperand);
                break;
            case "x^2":
                result = Calculator.square(firstOperand);
                break;
            case "sqrt":
                result = Calculator.squareRoot(firstOperand);
                break;
            case "+/-":
                result = Calculator.changeSign(firstOperand);
                break;
        }
        calculationDone = true;
        operatorClicked = false;
        return (result == Math.floor(result)) ? String.valueOf((int) result) : String.valueOf(result);
    }

    public void reset() {
        firstOperand = 0.0;
        operator = "";
        operatorClicked = false;
        calculationDone = false;
    }
}
