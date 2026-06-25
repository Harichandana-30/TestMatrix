import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ExamUI extends JFrame {

    JLabel questionLabel;
    JRadioButton option1, option2,
            option3, option4;

    ButtonGroup group;

    JButton nextButton;

    int currentQuestion = 0;
    int score = 0;

    String[] questions =
            new String[50];

    String[][] options =
            new String[50][4];

    String[] correctAnswers =
            new String[50];

    int totalQuestions = 0;

    public ExamUI() {

        setTitle("TestMatrix Exam");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(
                new Color(20, 20, 30));

        JLabel title =
                new JLabel(
                        "TestMatrix Examination");

        title.setBounds(
                180, 20, 350, 40);

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28));

        questionLabel =
                new JLabel();

        questionLabel.setBounds(
                50, 100, 600, 30);

        questionLabel.setForeground(
                Color.WHITE);

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18));

        option1 =
                new JRadioButton();

        option1.setBounds(
                80, 160, 400, 30);

        option2 =
                new JRadioButton();

        option2.setBounds(
                80, 200, 400, 30);

        option3 =
                new JRadioButton();

        option3.setBounds(
                80, 240, 400, 30);

        option4 =
                new JRadioButton();

        option4.setBounds(
                80, 280, 400, 30);

        group =
                new ButtonGroup();

        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        nextButton =
                new JButton("Next");

        nextButton.setBounds(
                250, 360, 150, 40);

        nextButton.addActionListener(
                e -> nextQuestion());

        panel.add(title);
        panel.add(questionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(nextButton);

        add(panel);

        loadQuestions();

        if (totalQuestions > 0) {
            showQuestion();
        }

        setVisible(true);
    }

    public void loadQuestions() {

        try {

            Connection connection =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM questions";

            var pst =
                    connection.prepareStatement(query);

            var result =
                    pst.executeQuery();

            while (result.next()) {

                questions[totalQuestions] =
                        result.getString("question");

                options[totalQuestions][0] =
                        result.getString("option1");

                options[totalQuestions][1] =
                        result.getString("option2");

                options[totalQuestions][2] =
                        result.getString("option3");

                options[totalQuestions][3] =
                        result.getString("option4");

                correctAnswers[totalQuestions] =
                        result.getString(
                                "correct_answer");

                totalQuestions++;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void showQuestion() {

        questionLabel.setText(
                questions[currentQuestion]);

        option1.setText(
                options[currentQuestion][0]);

        option2.setText(
                options[currentQuestion][1]);

        option3.setText(
                options[currentQuestion][2]);

        option4.setText(
                options[currentQuestion][3]);

        group.clearSelection();
    }

    public void nextQuestion() {

        String selectedAnswer = "";

        if (option1.isSelected())
            selectedAnswer =
                    option1.getText();

        else if (option2.isSelected())
            selectedAnswer =
                    option2.getText();

        else if (option3.isSelected())
            selectedAnswer =
                    option3.getText();

        else if (option4.isSelected())
            selectedAnswer =
                    option4.getText();

        if (selectedAnswer.equalsIgnoreCase(
                correctAnswers[currentQuestion])) {

            score++;
        }

        currentQuestion++;

        if (currentQuestion < totalQuestions) {

            showQuestion();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Exam Completed!\nScore: "
                            + score
                            + "/"
                            + totalQuestions);

            dispose();
        }
    }
}