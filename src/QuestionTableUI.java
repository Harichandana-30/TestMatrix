import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import javax.swing.table.DefaultTableCellRenderer;

public class QuestionTableUI extends JFrame {

    JTable table;

    DefaultTableModel model;

    public QuestionTableUI() {

        setTitle("Question Management");
        ImageIcon icon = new ImageIcon("./resources/logo.png");

        setSize(1000,550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel=new JPanel(new BorderLayout());

        panel.setBackground(new Color(18,18,35));

        JLabel heading=
                new JLabel(
                        "Question Management",
                        SwingConstants.CENTER);

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        heading.setForeground(
                new Color(215,110,255));

        panel.add(
                heading,
                BorderLayout.NORTH);

        model=
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Question");
        model.addColumn("Option 1");
        model.addColumn("Option 2");
        model.addColumn("Option 3");
        model.addColumn("Option 4");
        model.addColumn("Correct Answer");

        table=
                new JTable(model);
                table.setRowHeight(35);

table.setFont(new Font("Segoe UI", Font.PLAIN, 15));

table.setSelectionBackground(new Color(98, 0, 238));

table.setSelectionForeground(Color.WHITE);

table.setGridColor(new Color(210,210,210));

table.setShowGrid(true);

        JScrollPane scroll=
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

table.getColumnModel()
     .getColumn(0)
     .setCellRenderer(center);
table.getColumnModel().getColumn(0).setPreferredWidth(60);

table.getColumnModel().getColumn(1).setPreferredWidth(350);

table.getColumnModel().getColumn(6).setPreferredWidth(120);

        panel.add(
                scroll,
                BorderLayout.CENTER);

        JButton refresh=
                new JButton("Refresh");
        JButton delete =
                new JButton("Delete Selected");
        JButton edit =
                new JButton("Edit Selected");

        refresh.addActionListener(
                e->loadQuestions());
            delete.addActionListener(e -> {

    int row = table.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a question.");

        return;
    }

    int questionId =
            (int) model.getValueAt(row, 0);

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Delete this question?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {

        try {

            var connection =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM questions WHERE question_id=?";

            var pst =
                    connection.prepareStatement(query);

            pst.setInt(1, questionId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
        this,
        "Question Deleted!!",
        "Success",
        JOptionPane.INFORMATION_MESSAGE);

            loadQuestions();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
});

edit.addActionListener(e -> {

    int row = table.getSelectedRow();

    if(row==-1){

        JOptionPane.showMessageDialog(
                this,
                "Please select a question.");

        return;
    }

    new EditQuestionUI(

            (int)model.getValueAt(row,0),

            model.getValueAt(row,1).toString(),

            model.getValueAt(row,2).toString(),

            model.getValueAt(row,3).toString(),

            model.getValueAt(row,4).toString(),

            model.getValueAt(row,5).toString(),

            model.getValueAt(row,6).toString()

    );

});

        JPanel bottomPanel = new JPanel();

    bottomPanel.add(refresh);
    bottomPanel.add(edit);
    bottomPanel.add(delete);

panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);

        loadQuestions();

        setVisible(true);

    }

    private void loadQuestions(){

        try{

            model.setRowCount(0);

            Connection connection=
                    DBConnection.getConnection();

            String query=
                    "SELECT * FROM questions";

            var pst=
                    connection.prepareStatement(query);

            var rs=
                    pst.executeQuery();

            while(rs.next()){

                model.addRow(

                        new Object[]{

                                rs.getInt("question_id"),

                                rs.getString("question"),

                                rs.getString("option1"),

                                rs.getString("option2"),

                                rs.getString("option3"),

                                rs.getString("option4"),

                                rs.getString("correct_answer")

                        }

                );

            }

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}