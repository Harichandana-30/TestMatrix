import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;

public class StudentHistoryUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    int studentId;

    public StudentHistoryUI(int studentId) {

        this.studentId = studentId;

        setTitle("My Exam History");

        setSize(700,450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(new Color(18,18,35));

        JLabel title = new JLabel(
                "My Exam History",
                SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        title.setForeground(
                new Color(215,110,255));

        panel.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Score");
        model.addColumn("Exam Date");

        table = new JTable(model);

        table.setRowHeight(28);

        JScrollPane scroll =
                new JScrollPane(table);

        panel.add(scroll, BorderLayout.CENTER);

        JButton refresh =
                new JButton("Refresh");

        refresh.addActionListener(
                e -> loadHistory());

        panel.add(refresh, BorderLayout.SOUTH);

        add(panel);

        loadHistory();

        setVisible(true);

    }

    private void loadHistory() {

        try {

            model.setRowCount(0);

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT score, exam_date FROM results WHERE student_id=? ORDER BY exam_date DESC";

            var pst =
                    connection.prepareStatement(query);

            pst.setInt(1, studentId);

            var rs =
                    pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("score"),

                        rs.getTimestamp("exam_date")

                });

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}