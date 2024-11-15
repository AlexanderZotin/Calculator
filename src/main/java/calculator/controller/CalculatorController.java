package calculator.controller;

import calculator.model.CalculatorModel;
import calculator.view.View;

public interface CalculatorController {
    View getView();
    void cleanAllData();
    void removeLastDigit();
    void digitEntered(String digit);
    void pointEntered();
    void operationSelected(CalculatorModel.Operation action);
    void operationCountRequired();
    void squareRootRequired();
}
