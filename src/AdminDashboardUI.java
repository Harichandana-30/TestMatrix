import javax.swing.*;
import java.awt.*;

public class AdminDashboardUI extends JFrame {

    public AdminDashboardUI() {

        setTitle("Admin Dashboard");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(18,18,35));

        JLabel title=new JLabel("Admin Dashboard");
        title.setBounds(190,30,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setForeground(new Color(215,110,255));

        JButton addQuestion=new JButton("Add Question");
        JButton viewQuestions=new JButton("View Questions");
        JButton deleteQuestion=new JButton("Delete Question");
        JButton viewResults=new JButton("View Results");

        addQuestion.setBounds(180,110,300,45);
        viewQuestions.setBounds(180,180,300,45);
        deleteQuestion.setBounds(180,250,300,45);
        viewResults.setBounds(180,320,300,45);

        panel.add(title);
        panel.add(addQuestion);
        panel.add(viewQuestions);
        panel.add(deleteQuestion);
        panel.add(viewResults);

        add(panel);

        setVisible(true);

    }

}