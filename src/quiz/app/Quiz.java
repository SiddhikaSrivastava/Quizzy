package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {

    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];

    JLabel qno, question, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;

    JButton next, submit, help;
    Timer quizTimer;
    int timeLeft = 15;

    public static int count = 0;
    public static int score = 0;

    String name;

    Quiz(String name) {
        this.name = name;

        getContentPane().setBackground(new Color(0, 31, 63));
        setLayout(null);

        // Load Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(1500, 300, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1500, 300); // Adjusted height
        add(image);


        // Question number
        qno = new JLabel();
        qno.setBounds(100, 370, 50, 30);
        qno.setFont(new Font("Tahoma", Font.BOLD, 24));
        qno.setForeground(Color.WHITE);
        add(qno);

        // Question
        question = new JLabel();
        question.setBounds(150, 370, 1000, 30);
        question.setFont(new Font("Tahoma", Font.BOLD, 24));
        question.setForeground(Color.WHITE);
        add(question);

        // Timer label
        timerLabel = new JLabel("Time left: 15 seconds");
        timerLabel.setBounds(1100, 370, 300, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        timerLabel.setForeground(Color.RED);
        add(timerLabel);

        // Options
        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        JRadioButton[] options = {opt1, opt2, opt3, opt4};
        int y = 450;
        for (JRadioButton opt : options) {
            opt.setBounds(150, y, 800, 30);
            opt.setFont(new Font("Tahoma", Font.PLAIN, 20));
            opt.setBackground(Color.GRAY);
            opt.setForeground(Color.WHITE);
            add(opt);
            y += 40;
        }

        group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        // Buttons
        next = new JButton("Next");
        next.setBounds(810, 670, 200, 30);
        next.setBackground(new Color(22, 99, 54));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        help = new JButton("Help");
        help.setBounds(1030, 670, 200, 30);
        help.setBackground(Color.YELLOW);
        help.setForeground(Color.BLACK);
        help.addActionListener(this);
        add(help);

        submit = new JButton("Submit");
        submit.setBounds(1250, 670, 200, 30);
        submit.setBackground(Color.YELLOW);
        submit.setForeground(Color.BLACK);
        submit.setEnabled(false);
        submit.addActionListener(this);
        add(submit);

        // Set questions and answers
        setQA();

        start(count);

        // Timer
        quizTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + " seconds");

            if (timeLeft <= 0) {
                timeLeft = 15;
                recordAnswer();

                if (count == 9) {
                    calculateScore();
                    setVisible(false);
                    new Score(name, score);
                } else {
                    count++;
                    start(count);
                }
            }
        });
        quizTimer.start();

        setSize(1500, 780);
        setLocation(15, 20);
        setVisible(true);
    }

    void recordAnswer() {
        if (group.getSelection() == null) {
            useranswers[count][0] = "";
        } else {
            useranswers[count][0] = group.getSelection().getActionCommand();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            recordAnswer();

            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            count++;
            start(count);

        } else if (e.getSource() == help) {
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            help.setEnabled(false);

        } else if (e.getSource() == submit) {
            recordAnswer();
            calculateScore();
            quizTimer.stop();
            setVisible(false);
            new Score(name, score);
        }
    }

    void calculateScore() {
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equals(answers[i][1])) {
                score += 10;
            }
        }
        JOptionPane.showMessageDialog(this, "Your score is: " + score);
    }

    public void start(int count) {
        qno.setText((count + 1) + ". ");
        question.setText(questions[count][0]);

        opt1.setText(questions[count][1]);
        opt1.setActionCommand(questions[count][1]);

        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);

        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);

        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);

        group.clearSelection();
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
        help.setEnabled(true);
        timeLeft = 15;
    }

    public void setQA() {
        questions[0][0] = "Number of primitive data types in Java are.?";
        questions[0][1] = "6";   questions[0][2] = "7";
        questions[0][3] = "8";   questions[0][4] = "9";

        questions[1][0] = "What is the size of float and double in java.?";
        questions[1][1] = "32 and 64"; questions[1][2] = "32 and 32";
        questions[1][3] = "64 and 64"; questions[1][4] = "64 and 32";

        questions[2][0] = "Automatic type conversion is possible in which of the possible cases?";
        questions[2][1] = "Byte to int"; questions[2][2] = "Int to Long";
        questions[2][3] = "Long to int"; questions[2][4] = "Short to int";

        questions[3][0] = "When an array is passed to a method, what does the method receive?";
        questions[3][1] = "The reference of the array"; questions[3][2] = "A copy of the array";
        questions[3][3] = "Length of the array"; questions[3][4] = "Copy of first element";

        questions[4][0] = "Arrays in java are.?";
        questions[4][1] = "Object References"; questions[4][2] = "Objects";
        questions[4][3] = "Primitive data type"; questions[4][4] = "None";

        questions[5][0] = "When is the object created with new keyword?";
        questions[5][1] = "At run time"; questions[5][2] = "At compile time";
        questions[5][3] = "Depends on the code"; questions[5][4] = "None";

        questions[6][0] = "Identify the correct definition of a package.?";
        questions[6][1] = "A package is a collection of editing tools"; questions[6][2] = "A package is a collection of Classes";
        questions[6][3] = "A package is a collection of Classes and interfaces"; questions[6][4] = "A package is a collection of interfaces";

        questions[7][0] = "compareTo() returns";
        questions[7][1] = "True"; questions[7][2] = "False";
        questions[7][3] = "An int value"; questions[7][4] = "None";

        questions[8][0] = "To which of the following does the class string belong to.";
        questions[8][1] = "java.lang"; questions[8][2] = "java.awt";
        questions[8][3] = "java.applet"; questions[8][4] = "java.String";

        questions[9][0] = "Total constructor string class have.?";
        questions[9][1] = "3"; questions[9][2] = "7";
        questions[9][3] = "13"; questions[9][4] = "20";

        answers[0][1] = "8";
        answers[1][1] = "32 and 64";
        answers[2][1] = "Int to Long";
        answers[3][1] = "The reference of the array";
        answers[4][1] = "Objects";
        answers[5][1] = "At run time";
        answers[6][1] = "A package is a collection of Classes and interfaces";
        answers[7][1] = "An int value";
        answers[8][1] = "java.lang";
        answers[9][1] = "13";
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
