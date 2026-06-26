import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminDashboardUI extends JFrame {
    JLabel studentCountLabel;

JLabel questionCountLabel;

JLabel examCountLabel;

    public AdminDashboardUI() {

    setTitle("Admin Dashboard");
    ImageIcon icon = new ImageIcon("./resources/logo.png");

    setSize(1000,650);

    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(null);

    panel.setBackground(new Color(18,18,35));

    JLabel title = new JLabel("ADMIN DASHBOARD");

    title.setBounds(270,30,500,45);

    title.setFont(new Font("Segoe UI",Font.BOLD,34));

    title.setForeground(new Color(215,110,255));

    panel.add(title);

    JLabel subtitle = new JLabel("Manage your examination system");

    subtitle.setBounds(320,80,350,30);

    subtitle.setFont(new Font("Segoe UI",Font.PLAIN,18));

    subtitle.setForeground(Color.WHITE);

    panel.add(subtitle);
    studentCountLabel =
        createStatCard("👨 Students : 0");

studentCountLabel.setBounds(
        120,
        130,
        220,
        30);

panel.add(studentCountLabel);

questionCountLabel =
        createStatCard("❓ Questions : 0");

questionCountLabel.setBounds(
        390,
        130,
        220,
        30);

panel.add(questionCountLabel);

examCountLabel =
        createStatCard("📝 Exams : 0");

examCountLabel.setBounds(
        660,
        130,
        220,
        30);

panel.add(examCountLabel);

loadStatistics();
    JButton addButton = createCardButton(
            "➕ Add Question",
            new Color(52,152,219));

    JButton viewButton = createCardButton(
            "📋 View Questions",
            new Color(46,204,113));

    JButton resultButton = createCardButton(
            "📊 View Results",
            new Color(241,196,15));

    JButton logoutButton = createCardButton(
            "🚪 Logout",
            new Color(231,76,60));

    addButton.setBounds(120,170,300,120);

    viewButton.setBounds(540,170,300,120);

    resultButton.setBounds(120,340,300,120);

    logoutButton.setBounds(540,340,300,120);

    panel.add(addButton);

    panel.add(viewButton);

    panel.add(resultButton);

    panel.add(logoutButton);
    UIUtils.addHoverEffect(
        addButton,
        new Color(52,152,219),
        new Color(41,128,185));

UIUtils.addHoverEffect(
        viewButton,
        new Color(46,204,113),
        new Color(39,174,96));

UIUtils.addHoverEffect(
        resultButton,
        new Color(241,196,15),
        new Color(243,156,18));

UIUtils.addHoverEffect(
        logoutButton,
        new Color(231,76,60),
        new Color(192,57,43));

    addButton.addActionListener(e -> new AddQuestionUI());

    viewButton.addActionListener(e -> new QuestionTableUI());

    resultButton.addActionListener(e -> new ViewResultsUI());

    logoutButton.addActionListener(e -> {

        new WelcomeUI();

        dispose();

    });

    add(panel);

    setVisible(true);

}
private JButton createCardButton(String text, Color color){

    JButton button = new JButton(text);

    button.setBackground(color);

    button.setForeground(Color.WHITE);

    button.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    20));

    button.setFocusPainted(false);

    button.setBorderPainted(false);

    button.setCursor(
            new Cursor(Cursor.HAND_CURSOR));

    return button;

}
private JLabel createStatCard(String text){

    JLabel label = new JLabel(text);

    label.setOpaque(true);

    label.setBackground(new Color(45,45,70));

    label.setForeground(Color.WHITE);

    label.setHorizontalAlignment(
            SwingConstants.CENTER);

    label.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    16));

    return label;

}
private void loadStatistics(){

    try{

        Connection connection =
                DBConnection.getConnection();

        ResultSet rs;

        rs = connection.createStatement()
                .executeQuery(
                        "SELECT COUNT(*) FROM students");

        if(rs.next()){

            studentCountLabel.setText(
                    "👨 Students : "
                    + rs.getInt(1));

        }

        rs = connection.createStatement()
                .executeQuery(
                        "SELECT COUNT(*) FROM questions");

        if(rs.next()){

            questionCountLabel.setText(
                    "❓ Questions : "
                    + rs.getInt(1));

        }

        rs = connection.createStatement()
                .executeQuery(
                        "SELECT COUNT(*) FROM results");

        if(rs.next()){

            examCountLabel.setText(
                    "📝 Exams : "
                    + rs.getInt(1));

        }

    }

    catch(Exception e){

        e.printStackTrace();

    }

}
}
