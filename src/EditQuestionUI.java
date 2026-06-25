import javax.swing.*;
import java.awt.*;

public class EditQuestionUI extends JFrame {

    private int questionId;

    private JTextArea questionField;
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField answerField;

    public EditQuestionUI(

            int questionId,
            String question,
            String option1,
            String option2,
            String option3,
            String option4,
            String answer) {

        this.questionId = questionId;

        setTitle("Edit Question");
        setSize(650,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(18,18,35));

        JLabel title = new JLabel("Edit Question");
        title.setBounds(210,20,250,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(215,110,255));

        panel.add(title);

        int y = 80;

        questionField = new JTextArea(question);
        JScrollPane scroll = new JScrollPane(questionField);
        scroll.setBounds(70,y,500,70);
        panel.add(scroll);

        y += 90;

        option1Field = new JTextField(option1);
        option1Field.setBounds(70,y,500,35);
        panel.add(option1Field);

        y += 50;

        option2Field = new JTextField(option2);
        option2Field.setBounds(70,y,500,35);
        panel.add(option2Field);

        y += 50;

        option3Field = new JTextField(option3);
        option3Field.setBounds(70,y,500,35);
        panel.add(option3Field);

        y += 50;

        option4Field = new JTextField(option4);
        option4Field.setBounds(70,y,500,35);
        panel.add(option4Field);

        y += 50;

        answerField = new JTextField(answer);
        answerField.setBounds(70,y,500,35);
        panel.add(answerField);

        JButton update = new JButton("Update Question");
        update.setBounds(190,490,220,45);

        update.addActionListener(e -> updateQuestion());

        panel.add(update);

        add(panel);

        setVisible(true);
    }

    private void updateQuestion(){

        try{

            var connection =
                    DBConnection.getConnection();

            String query =
                    "UPDATE questions SET question=?, option1=?, option2=?, option3=?, option4=?, correct_answer=? WHERE question_id=?";

            var pst =
                    connection.prepareStatement(query);

            pst.setString(1,questionField.getText());
            pst.setString(2,option1Field.getText());
            pst.setString(3,option2Field.getText());
            pst.setString(4,option3Field.getText());
            pst.setString(5,option4Field.getText());
            pst.setString(6,answerField.getText());
            pst.setInt(7,questionId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Question Updated Successfully!");

            dispose();

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}