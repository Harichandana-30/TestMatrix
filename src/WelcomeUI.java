import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class WelcomeUI extends JFrame {

    public WelcomeUI() {

        setTitle("TestMatrix");
      ImageIcon icon = new ImageIcon("./resources/logo.png");
        setIconImage(icon.getImage());
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Background
        JPanel background = new JPanel();
        background.setLayout(null);
        background.setBackground(
                new Color(8, 8, 20));

        // Main Card Container
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBounds(220, 55, 550, 520);

        card.setBackground(
                new Color(18, 18, 35));

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(180, 0, 255),
                                2,
                                true),
                        new EmptyBorder(
                                20, 20, 20, 20)));

        // Logo
        JLabel logo =
                new JLabel("🎓");

        logo.setBounds(
                235, 20, 80, 80);

        logo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        42));

        // Title
        JLabel title =
                new JLabel("TestMatrix");

        title.setBounds(
                80, 90, 400, 60);

        title.setHorizontalAlignment(
                SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        52));

        title.setForeground(
                new Color(215, 110, 255));

        // Subtitle
        JLabel subtitle =
                new JLabel(
                        "Smart Online Examination Platform");

        subtitle.setBounds(
                70, 150, 420, 30);

        subtitle.setHorizontalAlignment(
                SwingConstants.CENTER);

        subtitle.setForeground(
                new Color(180, 180, 210));

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        JSeparator leftLine = new JSeparator();
leftLine.setBounds(110, 198, 110, 2);
leftLine.setForeground(new Color(180, 0, 255));
leftLine.setBackground(new Color(180, 0, 255));

JLabel dots = new JLabel("●  ●  ●");
dots.setBounds(220, 187, 110, 25);
dots.setHorizontalAlignment(SwingConstants.CENTER);
dots.setForeground(new Color(180, 0, 255));
dots.setFont(new Font("Segoe UI", Font.BOLD, 12));

JSeparator rightLine = new JSeparator();
rightLine.setBounds(330, 198, 110, 2);
rightLine.setForeground(new Color(180, 0, 255));
rightLine.setBackground(new Color(180, 0, 255));

card.add(leftLine);
card.add(dots);
card.add(rightLine);

        // Buttons
        JButton studentLogin =
                createButton(
                        "Student Login");

        studentLogin.setBounds(
                100, 230, 350, 55);

        JButton register =
                createButton(
                        "Student Register");
        register.addActionListener(
        e -> new StudentRegisterUI());

        register.setBounds(
                100, 305, 350, 55);

        JButton adminLogin =
                createButton(
                        "Admin Login");
        adminLogin.addActionListener(
        e -> new AdminLoginUI());

        adminLogin.setBounds(
                100, 380, 350, 55);

        JButton exit =
                createExitButton(
                        "Exit");

        exit.setBounds(
                100, 455, 350, 55);

        // Actions
        studentLogin.addActionListener(
                e -> new StudentLoginUI());

        exit.addActionListener(
                e -> System.exit(0));

        // Add components
        card.add(logo);
        card.add(title);
        card.add(subtitle);
        card.add(dots);

        card.add(studentLogin);
        card.add(register);
        card.add(adminLogin);
        card.add(exit);
        UIUtils.addHoverEffect(
        studentLogin,
        new Color(115, 0, 255),
        new Color(140, 40, 255));

UIUtils.addHoverEffect(
        register,
        new Color(115, 0, 255),
        new Color(140, 40, 255));

UIUtils.addHoverEffect(
        adminLogin,
        new Color(115, 0, 255),
        new Color(140, 40, 255));

UIUtils.addHoverEffect(
        exit,
        new Color(190, 0, 120),
        new Color(220, 30, 150));


        background.add(card);

        add(background);
        setVisible(true);
    }

    // Purple Buttons
    private JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        button.setBackground(
                new Color(115, 0, 255));

        button.setForeground(
                Color.WHITE);

        button.setBorder(
                new LineBorder(
                        new Color(
                                215, 110, 255),
                        2,
                        true));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }

    // Exit Button
    private JButton createExitButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        button.setBackground(
                new Color(190, 0, 120));

        button.setForeground(
                Color.WHITE);

        button.setBorder(
                new LineBorder(
                        new Color(
                                255, 80, 180),
                        2,
                        true));

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        return button;
    }
}