package calculator.controller;

import calculator.Utils;
import calculator.model.CalculatorModel;
import calculator.view.View;

import java.math.BigDecimal;
import java.util.Objects;

public class Controller implements CalculatorController {
    private final View view;
    private final CalculatorModel model;

    public Controller(View view, CalculatorModel model) {
        this.view = Objects.requireNonNull(view, "Параметр view не должен быть null!");
        this.model = Objects.requireNonNull(model, "Параметр model не должен быть null!");
    }

    @Override
    public void cleanAllData() {
        view.setMainDisplayText("0");
        view.setSecondDisplayText("");
        model.setOperation(null);
        model.setFirstNumber(BigDecimal.ZERO);
        model.setSecondNumber(BigDecimal.ZERO);
    }

    @Override
    public void removeLastDigit() {
        if(model.getOperation() == null) {
            model.setFirstNumber(Utils.removeLastDigit(model.getFirstNumber()));
            view.setMainDisplayText(Utils.removeLastDisplayCharacter(view.getMainDisplayText()));
        } else {
            model.setSecondNumber(Utils.removeLastDigit(model.getSecondNumber()));
            view.setMainDisplayText(Utils.removeLastDisplayCharacter(view.getMainDisplayText()));
        }
    }

    @Override
    public void digitEntered(String digit) {
        view.setMainDisplayText(view.getMainDisplayText() + digit);
        if(model.getOperation() == null) {
            model.setFirstNumber(new BigDecimal(view.getMainDisplayText()));
        } else {
            model.setSecondNumber(new BigDecimal(view.getMainDisplayText()));
        }
    }

    @Override
    public void pointEntered() {
        String mainDisplayText = view.getMainDisplayText();
        if(mainDisplayText.indexOf('.') < 0) view.setMainDisplayText(mainDisplayText + '.');
    }

    @Override
    public void operationSelected(CalculatorModel.Operation operation) {
        model.setOperation(operation);
        view.setMainDisplayText("0");
        view.setSecondDisplayText(model.getFirstNumber().toPlainString() + ' ' + operation);
    }

    @Override
    public void operationCountRequired() {
        if (model.getOperation() != null) {
            BigDecimal result = model.count();
            model.setFirstNumber(result);
            model.setSecondNumber(BigDecimal.ZERO);
            model.setOperation(null);
            view.setMainDisplayText(result.toPlainString());
            view.setSecondDisplayText("");
        }
    }

    @Override
    public void squareRootRequired() {
        model.setFirstNumber(model.countSquareRoot());
        view.setMainDisplayText(model.getFirstNumber().toPlainString());
        view.setSecondDisplayText("");
    }
}
