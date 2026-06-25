import javax.swing.*;
import java.awt.*;

public class StudentLoginUI extends JFrame {

    public StudentLoginUI() {

        setTitle("Student Login");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(
                new Color(25, 25, 35));

        JLabel title =
                new JLabel(
                        "Student Login");

        title.setBounds(
                150, 30, 250, 40);

        title.setForeground(
                Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28));

        JLabel emailLabel =
                new JLabel("Email");

        emailLabel.setBounds(
                70, 100, 100, 30);

        emailLabel.setForeground(
                Color.WHITE);

        JTextField emailField =
                new JTextField();

        emailField.setBounds(
                70, 130, 330, 35);

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setBounds(
                70, 180, 100, 30);

        passwordLabel.setForeground(
                Color.WHITE);

        JPasswordField passwordField =
                new JPasswordField();

        passwordField.setBounds(
                70, 210, 330, 35);

        JButton loginButton =
                new JButton("Login");

        loginButton.setBounds(
                150, 290, 180, 40);

        loginButton.addActionListener(e -> {

            try {

                String email =
                        emailField.getText();

                String password =
                        new String(
                                passwordField.getPassword());

                var connection =
                        DBConnection.getConnection();

                String query =
                        "SELECT * FROM students WHERE email=? AND password=?";

                var pst =
                        connection.prepareStatement(query);

                pst.setString(1, email);
                pst.setString(2, password);

                var result =
                        pst.executeQuery();
if (result.next()) {

    JOptionPane.showMessageDialog(
            this,
            "Login Successful!");

    int studentId =
            result.getInt("student_id");

    String studentName =
            result.getString("name");

    new ExamUI(
            studentId,
            studentName);

    dispose();

}else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid Email or Password!");
                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        panel.add(title);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }
}