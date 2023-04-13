import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;
import java.util.Random;

public class passwordGenerator {
    private JTextField textField1;
    private JButton generateButton;
    private JPanel panel1;
    private JTextField readyTextField;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        JFrame frame = new JFrame("Password Generator");
        frame.setContentPane(new passwordGenerator().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static String generatePassword (int length) {

        //minimum length of 6
        if (length < 4) {
            length = 6;
        }

        final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();
        final char[] symbols = "^$?!@#%&".toCharArray();
        final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();

        //Use cryptographically secure random number generator
        Random random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length-4; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
        password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
        password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
        password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
        password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);
        return password.toString();
    }

    public passwordGenerator() {

        generateButton.addActionListener(e -> {
            String input = textField1.getText();
            int chars = Integer.parseInt(input);
            readyTextField.setText(generatePassword(chars));
        });

        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
    }
}