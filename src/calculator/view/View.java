package calculator.view;

import java.awt.event.ActionListener;

public interface View {
    void setMainDisplayText(String text);
    String getMainDisplayText();
    void setSecondDisplayText(String text);
    String getSecondDisplayText();
    void setVisible(boolean visible);
    void subscribeToListener(ActionListener listener);
}
