import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class StudentRegisterUI extends JFrame {

    public StudentRegisterUI() {

        setTitle("Student Registration");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(18,18,35));

        JLabel title = new JLabel("Student Registration");
        title.setBounds(80,25,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(215,110,255));

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(60,90,120,25);
        nameLabel.setForeground(Color.WHITE);

        JTextField nameField = new JTextField();
        nameField.setBounds(60,115,360,38);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(60,170,120,25);
        emailLabel.setForeground(Color.WHITE);

        JTextField emailField = new JTextField();
        emailField.setBounds(60,195,360,38);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60,250,120,25);
        passwordLabel.setForeground(Color.WHITE);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(60,275,360,38);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120,360,240,45);

        registerButton.addActionListener(e->{

            try{

                String name=nameField.getText();
                String email=emailField.getText();
                String password=
                        new String(passwordField.getPassword());

                Connection connection=
                        DBConnection.getConnection();

                String query=
                        "INSERT INTO students(name,email,password) VALUES(?,?,?)";

                var pst=
                        connection.prepareStatement(query);

                pst.setString(1,name);
                pst.setString(2,email);
                pst.setString(3,password);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Successful!");

                dispose();

            }
            catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Registration Failed!");

                ex.printStackTrace();
            }

        });

        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);

        add(panel);
        setVisible(true);

    }

}