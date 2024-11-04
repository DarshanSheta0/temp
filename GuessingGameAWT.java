import java.awt.*;
import java.awt.event.*;

public class GuessingGameAWT extends Frame {
    private int computerNumber = (int) (Math.random() * 100 + 1);//0.0-1.0 and for adding 1  0-99.99 to 1-100.99
    private int count = 0;
    private Label feedbackLabel;
    private Label countLabel;

    public GuessingGameAWT() {
        setTitle("Guessing Game - AWT");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);// Centers the window on the screen.
        setVisible(true);
        
        // Header panel with label
        Panel headerPanel = new Panel();
        Label headerLabel = new Label("Guess the Number (1-100)", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Central panel for guess input and feedback
        Panel centerPanel = new Panel(new GridLayout(4, 1, 10, 10));//4-raw,1-col,gap-between 10 px
        centerPanel.setBackground(new Color(240, 255, 255));

        Label instructionLabel = new Label("Enter your guess:", Label.CENTER);
        TextField guessInput = new TextField(10);
        feedbackLabel = new Label("Feedback will appear here", Label.CENTER);
        feedbackLabel.setForeground(new Color(34, 139, 34));
        countLabel = new Label("Total Guesses: 0", Label.CENTER);

        centerPanel.add(instructionLabel);
        centerPanel.add(guessInput);
        centerPanel.add(feedbackLabel);
        centerPanel.add(countLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Footer panel with Guess and Reset buttons
        Panel footerPanel = new Panel();
        Button guessButton = new Button("Guess");
        Button resetButton = new Button("Reset");
        guessButton.setBackground(new Color(60, 179, 113));
        guessButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(255, 69, 0));
        resetButton.setForeground(Color.WHITE);
        footerPanel.add(guessButton);
        footerPanel.add(resetButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Action listener for the Guess button
        guessButton.addActionListener(new ActionListener() {
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

        // Action listener for the Reset button
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Window listener to handle close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Logic to determine the guess
    public String determineGuess(int userAnswer) {
        count++; // Increment guess count
        countLabel.setText("Total Guesses: " + count);
        
        if (userAnswer <= 0 || userAnswer > 100) {
            return "Your guess is invalid!";
        } else if (userAnswer == computerNumber) {
            String result = "Correct! Total Guesses: " + count + ". Click Reset to play again.";
            resetGame(); // Reset the game after a correct guess
            return result;
        } else if (userAnswer > computerNumber) {
            return "Your guess is too high. Try again.";
        } else {
            return "Your guess is too low. Try again.";
        }
    }

    // Method to reset the game
    private void resetGame() {
        computerNumber = (int) (Math.random() * 100 + 1);
        count = 0;
        countLabel.setText("Total Guesses: 0");
        feedbackLabel.setText("Feedback will appear here");
    }

    public static void main(String[] args) {
        new GuessingGameAWT();
    }
}