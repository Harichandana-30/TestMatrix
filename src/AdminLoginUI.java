import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLoginUI extends JFrame {

    public AdminLoginUI() {

        setTitle("Admin Login");
        ImageIcon icon = new ImageIcon("./resources/logo.png");
        setSize(500,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(18,18,35));

        JLabel title=new JLabel("Administrator Login");
        title.setBounds(80,30,350,40);
        title.setForeground(new Color(215,110,255));
        title.setFont(new Font("Segoe UI",Font.BOLD,28));

        JLabel userLabel=new JLabel("Username");
        userLabel.setBounds(60,100,120,25);
        userLabel.setForeground(Color.WHITE);

        JTextField usernameField=new JTextField();
        usernameField.setBounds(60,125,360,38);

        JLabel passLabel=new JLabel("Password");
        passLabel.setBounds(60,190,120,25);
        passLabel.setForeground(Color.WHITE);

        JPasswordField passwordField=new JPasswordField();
        passwordField.setBounds(60,215,360,38);

        JButton loginButton=new JButton("Login");
        loginButton.setBounds(120,300,240,45);

        loginButton.addActionListener(e->{

            String username=usernameField.getText();
            String password=
                    new String(passwordField.getPassword());

            if(username.equals("admin")
                    && password.equals("admin123")){

                JOptionPane.showMessageDialog(
                        this,
                        "Admin Login Successful!");

                new AdminDashboardUI();

                dispose();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Credentials!");
            }

        });

        panel.add(title);
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        setVisible(true);

    }

}