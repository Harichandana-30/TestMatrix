import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StudentLoginUI extends JFrame {

        public StudentLoginUI() {

                setTitle("Student Login");

                ImageIcon icon = new ImageIcon("./resources/logo.png");

                setIconImage(icon.getImage());

                setSize(900, 650);

                setLocationRelativeTo(null);

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                setResizable(false);

                JPanel background = new JPanel(null);

                background.setBackground(
                                new Color(8, 8, 20));

                JPanel card = new JPanel(null);

                card.setBounds(225, 70, 450, 480);

                card.setBackground(
                                new Color(20, 20, 38));

                card.setBorder(
                                BorderFactory.createCompoundBorder(

                                                new LineBorder(
                                                                new Color(180, 0, 255),
                                                                2,
                                                                true),

                                                new EmptyBorder(
                                                                20, 20, 20, 20)));

                JLabel title = new JLabel("👨‍🎓 Student Login");

                title.setBounds(
                                70,
                                30,
                                320,
                                40);

                title.setHorizontalAlignment(
                                SwingConstants.CENTER);

                title.setForeground(
                                new Color(215, 110, 255));

                title.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                30));

                JLabel subtitle = new JLabel("Welcome Back!");

                subtitle.setBounds(
                                100,
                                75,
                                250,
                                25);

                subtitle.setHorizontalAlignment(
                                SwingConstants.CENTER);

                subtitle.setForeground(
                                Color.LIGHT_GRAY);

                subtitle.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                17));

                JLabel emailLabel = new JLabel("📧 Email");

                emailLabel.setBounds(
                                55,
                                130,
                                200,
                                25);

                emailLabel.setForeground(
                                Color.WHITE);

                emailLabel.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                16));

                JTextField emailField = new JTextField();

                emailField.setBounds(
                                55,
                                160,
                                340,
                                42);

                emailField.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                16));

                emailField.setBorder(
                                BorderFactory.createLineBorder(
                                                new Color(160, 90, 255),
                                                2));

                JLabel passwordLabel = new JLabel("🔒 Password");

                passwordLabel.setBounds(
                                55,
                                225,
                                200,
                                25);

                passwordLabel.setForeground(
                                Color.WHITE);

                passwordLabel.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                16));

                JPasswordField passwordField = new JPasswordField();

                passwordField.setBounds(
                                55,
                                255,
                                340,
                                42);

                passwordField.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                16));

                passwordField.setBorder(
                                BorderFactory.createLineBorder(
                                                new Color(160, 90, 255),
                                                2));

                JButton loginButton = new JButton("Login");

                loginButton.setBounds(
                                120,
                                335,
                                210,
                                48);

                loginButton.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                18));

                JButton backButton = new JButton("← Back");

                backButton.setBounds(
                                120,
                                400,
                                210,
                                42);

                backButton.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                16));
                loginButton.addActionListener(e -> {

                        try {

                                String email = emailField.getText();

                                String password = new String(
                                                passwordField.getPassword());

                                var connection = DBConnection.getConnection();

                                String query = "SELECT * FROM students WHERE email=? AND password=?";

                                var pst = connection.prepareStatement(query);

                                pst.setString(1, email);

                                pst.setString(2, password);

                                var result = pst.executeQuery();

                                if (result.next()) {

                                        JOptionPane.showMessageDialog(
                                                        this,
                                                        "Login Successful!",
                                                        "Success",
                                                        JOptionPane.INFORMATION_MESSAGE);

                                        int studentId = result.getInt("student_id");

                                        String studentName = result.getString("name");

                                        new StudentDashboardUI(
                                                        studentId,
                                                        studentName);

                                        dispose();

                                }

                                else {

                                        JOptionPane.showMessageDialog(
                                                        this,
                                                        "Invalid Email or Password!",
                                                        "Login Failed",
                                                        JOptionPane.ERROR_MESSAGE);

                                }

                        }

                        catch (Exception ex) {

                                ex.printStackTrace();

                        }

                });

                backButton.addActionListener(e -> {

                        new WelcomeUI();

                        dispose();

                });

                UIUtils.addHoverEffect(
                                loginButton,
                                new Color(115, 0, 255),
                                new Color(145, 40, 255));

                UIUtils.addHoverEffect(
                                backButton,
                                new Color(70, 70, 90),
                                new Color(95, 95, 120));

                loginButton.setBackground(
                                new Color(115, 0, 255));

                loginButton.setForeground(Color.WHITE);

                backButton.setBackground(
                                new Color(70, 70, 90));

                backButton.setForeground(Color.WHITE);

                card.add(title);

                card.add(subtitle);

                card.add(emailLabel);

                card.add(emailField);

                card.add(passwordLabel);

                card.add(passwordField);

                card.add(loginButton);

                card.add(backButton);

                background.add(card);

                add(background);

                setVisible(true);

        }

}