import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) throws Exception
    {
        Frame frame = new Frame("Flappy Bird");
        frame.setSize(360, 640);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);// Centers thgit e window on the screen.
        
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();//Sizes the frame to fit its content
        flappyBird.requestFocus();//whatever we added component in frame,it is focus on the component,Eg.FlappyBird.
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
    }
}