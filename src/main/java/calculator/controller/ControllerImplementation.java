package calculator.controller;

import calculator.Utils;
import calculator.model.CalculatorModel;
import calculator.view.View;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;

@NonNull
@AllArgsConstructor
public class ControllerImplementation implements CalculatorController {
    private final @Getter View view;
    private final CalculatorModel model;

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
    public void digitEntered(@NonNull String digit) {
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
