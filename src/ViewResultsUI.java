import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewResultsUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewResultsUI() {

        setTitle("Student Results");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(18,18,35));

        JLabel title = new JLabel("Student Results", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(215,110,255));

        panel.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Score");
        model.addColumn("Exam Date");

        table = new JTable(model);

        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> loadResults());

        panel.add(refreshButton, BorderLayout.SOUTH);

        add(panel);

        loadResults();

        setVisible(true);
    }

    private void loadResults() {

        try {

            model.setRowCount(0);

            var connection = DBConnection.getConnection();

            String query =
                    "SELECT s.student_id, s.name, s.email, r.score, r.exam_date " +
                    "FROM students s " +
                    "JOIN results r ON s.student_id = r.student_id";

            var pst = connection.prepareStatement(query);

            var rs = pst.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[] {

                        rs.getInt("student_id"),

                        rs.getString("name"),

                        rs.getString("email"),

                        rs.getInt("score"),

                        rs.getTimestamp("exam_date")

                });

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}