import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuessingGameSwing extends JFrame {
    private int computerNumber = (int) (Math.random() * 100 + 1);
    private int count = 1;

    // Constructor to set up the GUI
    public GuessingGameSwing() {
        // Main frame setup
        setTitle("Guessing Game - Swing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header panel with label
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Guess the Number (1-100)");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Central panel for guess input and feedback
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel instructionLabel = new JLabel("Enter your guess:");
        JTextField guessInput = new JTextField(10);
        JLabel feedbackLabel = new JLabel("Feedback will appear here", SwingConstants.CENTER);
        feedbackLabel.setForeground(new Color(34, 139, 34));
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        centerPanel.add(instructionLabel);
        centerPanel.add(guessInput);
        centerPanel.add(feedbackLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Footer panel with Guess button
        JPanel footerPanel = new JPanel();
        JButton guessButton = new JButton("Guess");
        guessButton.setBackground(new Color(60, 179, 113));
        guessButton.setForeground(Color.WHITE);
        footerPanel.add(guessButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Action listener for the Guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String response = guessInput.getText();
                try {
                    int userAnswer = Integer.parseInt(response);
                    String feedback = determineGuess(userAnswer);
                    feedbackLabel.setText(feedback);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
                guessInput.setText("");
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    // Logic to determine the guess
    public String determineGuess(int userAnswer) {
        if (userAnswer <= 0 || userAnswer > 100) {
            return "Your guess is invalid!";
        } else if (userAnswer == computerNumber) {
            return "Correct! Total Guesses: " + count;
        } else if (userAnswer > computerNumber) {
            count++;
            return "Your guess is too high. Try again.";
        } else {
            count++;
            return "Your guess is too low. Try again.";
        }
    }

    // Main method to launch the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessingGameSwing());
    }
}
