import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ExamUI extends JFrame {

    JLabel titleLabel;

JLabel questionNumberLabel;

JLabel questionLabel;

JProgressBar progressBar;

JRadioButton option1;
JRadioButton option2;
JRadioButton option3;
JRadioButton option4;

ButtonGroup group;

JButton previousButton;
JButton nextButton;
JButton submitButton;
JLabel timerLabel;
Timer timer;
int timeRemaining = 20 * 60; // 20 minutes

    int currentQuestion = 0;
    int score = 0;
    int studentId;
    String studentName;

    String[] questions =
            new String[50];

    String[][] options =
            new String[50][4];

    String[] correctAnswers =
            new String[50];
    String[] selectedAnswers =
            new String[50];

    int totalQuestions = 0;

    public ExamUI(int studentId, String studentName) {

    this.studentId = studentId;
    this.studentName = studentName;

    setTitle("TestMatrix Examination");

    setSize(900,650);

    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();

    panel.setLayout(null);

    panel.setBackground(new Color(18,18,35));

    titleLabel = new JLabel(
            "TestMatrix Examination");

    titleLabel.setBounds(
            220,20,450,40);

    titleLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    30));

    titleLabel.setForeground(
            new Color(215,110,255));

    panel.add(titleLabel);

    questionNumberLabel =
            new JLabel("Question 1");

    questionNumberLabel.setBounds(
            60,80,200,25);

    questionNumberLabel.setForeground(Color.WHITE);

    questionNumberLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    18));

    panel.add(questionNumberLabel);
    timerLabel = new JLabel();

timerLabel.setBounds(600, 40, 220, 30);

timerLabel.setForeground(new Color(255, 215, 0));

timerLabel.setFont(
        new Font(
                "Segoe UI",
                Font.BOLD,
                18));

panel.add(timerLabel);

    progressBar =
            new JProgressBar();

    progressBar.setBounds(
            600,80,220,25);

    progressBar.setStringPainted(true);

    panel.add(progressBar);

    questionLabel =
            new JLabel();

    questionLabel.setBounds(
            60,140,760,40);

    questionLabel.setForeground(Color.WHITE);

    questionLabel.setFont(
            new Font(
                    "Segoe UI",
                    Font.PLAIN,
                    20));

    panel.add(questionLabel);

    option1 = new JRadioButton();

    option2 = new JRadioButton();

    option3 = new JRadioButton();

    option4 = new JRadioButton();

    option1.setBounds(90,220,500,35);

    option2.setBounds(90,270,500,35);

    option3.setBounds(90,320,500,35);

    option4.setBounds(90,370,500,35);

    JRadioButton[] options = {

            option1,

            option2,

            option3,

            option4

    };

    for(JRadioButton rb : options){

        rb.setBackground(new Color(18,18,35));

        rb.setForeground(Color.WHITE);

        rb.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        18));

        panel.add(rb);

    }

    group = new ButtonGroup();

    group.add(option1);

    group.add(option2);

    group.add(option3);

    group.add(option4);

    previousButton =
            new JButton("Previous");

    nextButton =
            new JButton("Next");

    submitButton =
            new JButton("Submit");

    previousButton.setBounds(
            180,500,150,45);

    nextButton.setBounds(
            360,500,150,45);

    submitButton.setBounds(
            540,500,150,45);

    panel.add(previousButton);

    panel.add(nextButton);

    panel.add(submitButton);
    nextButton.addActionListener(
        e -> nextQuestion());
submitButton.addActionListener(
        e -> submitExam());
previousButton.addActionListener(e -> {

    saveSelectedAnswer();

    if(currentQuestion > 0){

        currentQuestion--;

        showQuestion();

    }

});

    add(panel);

    loadQuestions();
    startTimer();

    if(totalQuestions>0){

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

    questionNumberLabel.setText(
            "Question "
            + (currentQuestion + 1)
            + " of "
            + totalQuestions);

    progressBar.setMaximum(totalQuestions);

    progressBar.setValue(currentQuestion + 1);

    questionLabel.setText(
            "<html><body style='width:700px'>"
            + questions[currentQuestion]
            + "</body></html>");

    option1.setText(options[currentQuestion][0]);
    option2.setText(options[currentQuestion][1]);
    option3.setText(options[currentQuestion][2]);
    option4.setText(options[currentQuestion][3]);

    // Clear previous selection
    group.clearSelection();

    // Restore previously selected answer
    if(selectedAnswers[currentQuestion] != null){

        if(option1.getText().equals(selectedAnswers[currentQuestion]))
            option1.setSelected(true);

        else if(option2.getText().equals(selectedAnswers[currentQuestion]))
            option2.setSelected(true);

        else if(option3.getText().equals(selectedAnswers[currentQuestion]))
            option3.setSelected(true);

        else if(option4.getText().equals(selectedAnswers[currentQuestion]))
            option4.setSelected(true);

    }

}


private void saveSelectedAnswer() {

    if (option1.isSelected())
        selectedAnswers[currentQuestion] = option1.getText();

    else if (option2.isSelected())
        selectedAnswers[currentQuestion] = option2.getText();

    else if (option3.isSelected())
        selectedAnswers[currentQuestion] = option3.getText();

    else if (option4.isSelected())
        selectedAnswers[currentQuestion] = option4.getText();

}

private void startTimer() {

    timer = new Timer(1000, e -> {

        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;

        timerLabel.setText(
                String.format(
                        "⏱ Time Left : %02d:%02d",
                        minutes,
                        seconds));

        timeRemaining--;

        if (timeRemaining < 0) {

            timer.stop();

            JOptionPane.showMessageDialog(
                    this,
                    "Time is up!\nSubmitting exam...");

            finishExam();
        }

    });

    timer.start();

}

public void nextQuestion() {

    saveSelectedAnswer();

    currentQuestion++;

    if (currentQuestion < totalQuestions) {

        showQuestion();

    } else {

        submitExam();

    }

}
private void finishExam() {

    if (timer != null) {

        timer.stop();

    }
    try {

    Connection connection =
            DBConnection.getConnection();

    String query =
            "INSERT INTO results(student_id, score, exam_date) VALUES (?, ?, NOW())";

    var pst =
            connection.prepareStatement(query);

    pst.setInt(1, studentId);
    pst.setInt(2, score);

    pst.executeUpdate();

}
catch (Exception e) {

    e.printStackTrace();

}
    new ResultUI(
        studentId,
        studentName,
        score,
        totalQuestions);

    dispose();

}

private void submitExam() {

    saveSelectedAnswer();

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to submit the exam?",
            "Submit Exam",
            JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {

        if (timer != null) {
            timer.stop();
        }

        score = 0;

        for (int i = 0; i < totalQuestions; i++) {

            if (selectedAnswers[i] != null &&
                selectedAnswers[i].equalsIgnoreCase(correctAnswers[i])) {

                score++;

            }

        }

        finishExam();

    }

}

}