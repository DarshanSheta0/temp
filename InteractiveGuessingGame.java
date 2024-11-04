import java.awt.*;
import java.awt.event.*;

public class InteractiveGuessingGame extends Frame {
    private int computerNumber;
    private int count;
    private Label feedbackLabel;
    private TextField guessInput;
    
    public InteractiveGuessingGame() {
        setTitle("Interactive Guessing Game");
        setSize(400, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // Start a new game
        startNewGame();

        // Header panel with label
        Panel headerPanel = new Panel();
        Label headerLabel = new Label("Guess the Number (1-100)", Label.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Central panel for guess input and feedback
        Panel centerPanel = new Panel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 255, 255));

        Label instructionLabel = new Label("Enter your guess:", Label.CENTER);
        guessInput = new TextField(10);
        feedbackLabel = new Label("Feedback will appear here", Label.CENTER);
        feedbackLabel.setForeground(new Color(34, 139, 34));

        centerPanel.add(instructionLabel);
        centerPanel.add(guessInput);
        centerPanel.add(feedbackLabel);
        
        Button guessButton = new Button("Guess");
        centerPanel.add(guessButton); // Add Guess button to center panel
        add(centerPanel, BorderLayout.CENTER);

        // Action listener for the Guess button
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String response = guessInput.getText();
                try {
                    int userAnswer = Integer.parseInt(response);
                    String feedback = determineGuess(userAnswer);
                    feedbackLabel.setText(feedback);
                    guessInput.setText("");
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number.");
                }
            }
        });

        // Window listener to handle close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    // Start a new game
    private void startNewGame() {
        computerNumber = (int) (Math.random() * 100 + 1); // Random number between 1-100
        count = 0; // Reset guess count
        feedbackLabel.setText("New game started! Try to guess the number!");
        setBackground(Color.WHITE); // Reset background color
    }

    // Logic to determine the guess
    public String determineGuess(int userAnswer) {
        count++;
        
        if (userAnswer < 1 || userAnswer > 100) {
            return "Your guess is out of bounds!";
        } else if (userAnswer == computerNumber) {
            String result = "Correct! Total Guesses: " + count + ".";
            startNewGame(); // Restart the game
            return result;
        } else {
            if (userAnswer > computerNumber) {
                setBackground(Color.RED); // Too high
                return "Your guess is too high. Try again.";
            } else {
                setBackground(Color.BLUE); // Too low
                return "Your guess is too low. Try again.";
            }
        }
    }

    public static void main(String[] args) {
        new InteractiveGuessingGame();
    }
}
