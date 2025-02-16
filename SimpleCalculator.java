import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Set background color to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Create the text field for displaying the input/output
        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Create the panel for the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.BLACK);

        // Button colors and action listeners
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.PLAIN, 25));
            button.addActionListener(new ButtonClickListener(display, text));
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    static class ButtonClickListener implements ActionListener {
        private JTextField display;
        private String buttonText;

        public ButtonClickListener(JTextField display, String buttonText) {
            this.display = display;
            this.buttonText = buttonText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String currentText = display.getText();
            if (buttonText.equals("=")) {
                try {
                    display.setText(eval(currentText));
                } catch (Exception ex) {
                    display.setText("Error");
                }
            } else if (buttonText.equals("C")) {
                display.setText("");
            } else {
                display.setText(currentText + buttonText);
            }
        }

        private String eval(String expression) {
            try {
                // Basic calculation evaluation
                return String.valueOf(new javax.script.ScriptEngineManager()
                        .getEngineByName("JavaScript")
                        .eval(expression));
            } catch (Exception e) {
                return "Error";
            }
        }
    }
}
