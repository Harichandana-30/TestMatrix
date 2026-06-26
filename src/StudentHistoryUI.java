import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import javax.swing.table.DefaultTableCellRenderer;

public class StudentHistoryUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    int studentId;

    public StudentHistoryUI(int studentId) {

        this.studentId = studentId;

        setTitle("My Exam History");
        ImageIcon icon = new ImageIcon("./resources/logo.png");

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
        table.setRowHeight(35);

table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

table.setSelectionBackground(new Color(98,0,238));

table.setSelectionForeground(Color.WHITE);

table.setGridColor(new Color(210,210,210));

table.setShowGrid(true);

        table.setRowHeight(28);

        JScrollPane scroll =
                new JScrollPane(table);
                table.getTableHeader().setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                16));

table.getTableHeader().setBackground(
        new Color(70,70,150));

table.getTableHeader().setForeground(
        Color.WHITE);

table.getTableHeader().setReorderingAllowed(false);
DefaultTableCellRenderer center =
        new DefaultTableCellRenderer();

center.setHorizontalAlignment(
        SwingConstants.CENTER);

table.getColumnModel().getColumn(0).setCellRenderer(center);
table.getColumnModel().getColumn(0).setPreferredWidth(80);

table.getColumnModel().getColumn(1).setPreferredWidth(220);

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