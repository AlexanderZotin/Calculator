package calculator.controller;

import calculator.Utils;
import calculator.model.CalculatorModel;
import calculator.model.TooLargeNumberException;
import calculator.view.View;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Listener implements ActionListener {
    private final CalculatorController linkedController;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        View view = linkedController.getView();
        if(Utils.isPositiveNum(pressedButton.getText()))
            numButtonPressed(pressedButton);
        else if(pressedButton.equals(view.getGoButton()))
            goButtonPressed();
        else if(pressedButton.equals(view.getArrowButton()))
            linkedController.removeLastDigit();
        else if (pressedButton.equals(view.getCleanButton()))
            linkedController.cleanAllData();
        else if(pressedButton.equals(view.getSquareRootButton()))
            squareRootButtonPressed();
        else if (pressedButton.equals(view.getPointButton()))
            linkedController.pointEntered();
        else
            operationButtonPressed(pressedButton);
    }

    private void numButtonPressed(JButton pressedButton) {
        String mainDisplayText = linkedController.getView().getMainDisplayText();
        String pressedButtonText = pressedButton.getText();
        if(mainDisplayText.equals("0")) {
            linkedController.getView().setMainDisplayText("");
        } else if (mainDisplayText.endsWith(".0") && !mainDisplayText.contains(".")) {
            linkedController.removeLastDigit();
        }
        linkedController.digitEntered(pressedButtonText);
    }

    private void goButtonPressed() {
        try {
            linkedController.operationCountRequired();
        } catch (ArithmeticException | TooLargeNumberException | UnsupportedOperationException exception) {
            JOptionPane.showMessageDialog((Component) linkedController.getView(),
                    exception.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void squareRootButtonPressed() {
        try {
            linkedController.squareRootRequired();
        } catch (ArithmeticException exception) {
            JOptionPane.showMessageDialog((Component) linkedController.getView(),
                    exception.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void operationButtonPressed(JButton pressedButton) {
        linkedController.operationSelected
                (CalculatorModel.Operation.getOperationByDesignation(pressedButton.getText().charAt(0)));
    }
}
