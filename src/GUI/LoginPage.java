package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 
public class LoginPage {
    
    public static void main(String[] args) {    
        JFrame frame = new JFrame("Login");

        frame.setSize(300, 175);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    

        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);
        
        // user label
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(20,20,80,25);
        panel.add(userLabel);
        
        // user input
        JTextField userText = new JTextField(20);
        userText.setBounds(110,20,165,25);
        panel.add(userText);

        // psw label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20,50,80,25);
        panel.add(passwordLabel);

        // psw input
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(110,50,165,25);
        panel.add(passwordText);

        // login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(60, 100, 80, 25);
        panel.add(loginButton);
        
        // sign up button
        JButton signUpButton = new JButton("login");
        signUpButton.setBounds(160, 100, 80, 25);
        panel.add(signUpButton);
    }

}