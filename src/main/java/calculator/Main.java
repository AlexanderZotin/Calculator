package calculator;

import javax.swing.SwingUtilities;

import calculator.controller.ControllerImplementation;
import calculator.model.Engine;
import calculator.controller.Listener;
import calculator.view.Window;

public class Main {
    public static void main(String [] args) {
        SwingUtilities.invokeLater(() -> {
            var view = new Window();
            var controller = new ControllerImplementation(view, new Engine());
            view.subscribeToListener(new Listener(controller));
            view.setVisible(true);
        });
    }
}
