package calculator.controller;

import calculator.model.CalculatorModel;

public interface CalculatorController {
    void cleanAllData();
    void removeLastDigit();
    void digitEntered(String digit);
    void pointEntered();
    void operationSelected(CalculatorModel.Operation action);
    void operationCountRequired();
    void squareRootRequired();
}
