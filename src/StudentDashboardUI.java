import javax.swing.*;
import java.awt.*;

public class StudentDashboardUI extends JFrame {

    private final int studentId;
    private final String studentName;

    public StudentDashboardUI(int studentId, String studentName) {

        this.studentId = studentId;
        this.studentName = studentName;

        setTitle("Student Dashboard");
        ImageIcon icon = new ImageIcon("./resources/logo.png");

        setSize(750,520);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        panel.setBackground(new Color(18,18,35));

        JLabel title =
                new JLabel("Welcome, " + studentName);

        title.setBounds(180,30,400,40);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30));

        title.setForeground(
                new Color(215,110,255));

        panel.add(title);

        JButton exam =
                new JButton("Take Exam");

        JButton history =
                new JButton("My History");

        JButton logout =
                new JButton("Logout");

        exam.setBounds(220,120,280,55);

        history.setBounds(220,210,280,55);

        logout.setBounds(220,300,280,55);

        Font buttonFont =
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18);

        exam.setFont(buttonFont);
        history.setFont(buttonFont);
        logout.setFont(buttonFont);

        panel.add(exam);
        panel.add(history);
        panel.add(logout);
        UIUtils.addHoverEffect(
        exam,
        new Color(52,152,219),
        new Color(41,128,185));

UIUtils.addHoverEffect(
        history,
        new Color(46,204,113),
        new Color(39,174,96));

UIUtils.addHoverEffect(
        logout,
        new Color(231,76,60),
        new Color(192,57,43));

        exam.addActionListener(e -> {

            new ExamUI(studentId, studentName);

        });

        history.addActionListener(e -> {

            new StudentHistoryUI(studentId);

        });

        logout.addActionListener(e -> {

            new WelcomeUI();

            dispose();

        });

        add(panel);

        setVisible(true);

    }

}