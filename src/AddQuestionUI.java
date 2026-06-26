import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class AddQuestionUI extends JFrame {

    JTextArea questionArea;

    JTextField option1Field;
    JTextField option2Field;
    JTextField option3Field;
    JTextField option4Field;

    JTextField answerField;

    public AddQuestionUI() {

        setTitle("Add Question");
        ImageIcon icon = new ImageIcon("./resources/logo.png");

        setSize(700,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);

        panel.setBackground(new Color(18,18,35));

        JLabel title =
                new JLabel("Add New Question");

        title.setBounds(180,20,350,40);

        title.setForeground(new Color(215,110,255));

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30));

        panel.add(title);

        JLabel qLabel =
                new JLabel("Question");

        qLabel.setBounds(60,90,150,25);

        qLabel.setForeground(Color.WHITE);

        panel.add(qLabel);

        questionArea =
                new JTextArea();

        JScrollPane scroll =
                new JScrollPane(questionArea);

        scroll.setBounds(60,120,560,90);

        panel.add(scroll);

        int y = 230;

        option1Field = createField(panel,"Option 1",y);

        y += 70;

        option2Field = createField(panel,"Option 2",y);

        y += 70;

        option3Field = createField(panel,"Option 3",y);

        y += 70;

        option4Field = createField(panel,"Option 4",y);

        y += 70;

        answerField = createField(panel,"Correct Answer",y);

        JButton save =
                new JButton("Save Question");

        save.setBounds(220,610,220,45);

        save.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        panel.add(save);
        UIUtils.addHoverEffect(
        save,
        new Color(46,204,113),
        new Color(39,174,96));

        save.addActionListener(
                e -> saveQuestion());

        add(panel);

        setVisible(true);

    }

    private JTextField createField(JPanel panel,String label,int y){

        JLabel l =
                new JLabel(label);

        l.setBounds(60,y,150,25);

        l.setForeground(Color.WHITE);

        panel.add(l);

        JTextField field =
                new JTextField();

        field.setBounds(60,y+25,560,35);

        panel.add(field);

        return field;

    }

    private void saveQuestion(){

        try{

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "INSERT INTO questions(question,option1,option2,option3,option4,correct_answer) VALUES(?,?,?,?,?,?)";

            var pst =
                    connection.prepareStatement(query);

            pst.setString(1,questionArea.getText());

            pst.setString(2,option1Field.getText());

            pst.setString(3,option2Field.getText());

            pst.setString(4,option3Field.getText());

            pst.setString(5,option4Field.getText());

            pst.setString(6,answerField.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
        this,
        "Question Added",
        "Success",
        JOptionPane.INFORMATION_MESSAGE);

            questionArea.setText("");

            option1Field.setText("");

            option2Field.setText("");

            option3Field.setText("");

            option4Field.setText("");

            answerField.setText("");

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}