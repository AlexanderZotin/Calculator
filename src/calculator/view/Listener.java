package calculator.view;

import calculator.Main;
import calculator.Utils;
import calculator.model.CalculatorModel;
import calculator.model.TooLargeNumberException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Listener implements ActionListener {
    private final Window window;

    public Listener(Window window) {
        this.window = Objects.requireNonNull(window, "Параметр window не должен быть null!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        if(Utils.isPositiveNum(pressedButton.getText()))
            numButtonPressed(pressedButton);
        else if(pressedButton.equals(window.getGoButton()))
            goButtonPressed();
        else if(pressedButton.equals(window.getArrowButton()))
            Main.getController().removeLastDigit();
        else if (pressedButton.equals(window.getCleanButton()))
            Main.getController().cleanAllData();
        else if(pressedButton.equals(window.getSquareRootButton()))
            squareRootButtonPressed();
        else if (pressedButton.equals(window.getPointButton()))
            Main.getController().pointEntered();
        else
            operationButtonPressed(pressedButton);
    }

    private void numButtonPressed(JButton pressedButton) {
        String mainDisplayText = window.getMainDisplayText();
        String pressedButtonText = pressedButton.getText();
        if(mainDisplayText.equals("0")) {
            window.setMainDisplayText("");
        } else if (mainDisplayText.endsWith(".0")) {
            Main.getController().removeLastDigit();
        }
        Main.getController().digitEntered(pressedButtonText);
    }

    private void goButtonPressed() {
        try {
            Main.getController().operationCountRequired();
        } catch (ArithmeticException | TooLargeNumberException | UnsupportedOperationException exception) {
            JOptionPane.showMessageDialog(window, exception.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void squareRootButtonPressed() {
        try {
            Main.getController().squareRootRequired();
        } catch (ArithmeticException exception) {
            JOptionPane.showMessageDialog(window, exception.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void operationButtonPressed(JButton pressedButton) {
        Main.getController().operationSelected
                (CalculatorModel.Operation.getOperationByDesignation(pressedButton.getText().charAt(0)));
    }
}
