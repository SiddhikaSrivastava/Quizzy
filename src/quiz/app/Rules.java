package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton start, back;
    String name;

    Rules(String name) {
        this.name = name;

        // Window setup
        getContentPane().setBackground(new Color(0, 31, 63));
        setLayout(null);
        setSize(1500, 780);
        setLocation(15, 20);

        // Heading
        JLabel heading = new JLabel("Welcome " + name + " to QUIZ TEST");
        heading.setBounds(300, 50, 900, 60);
        heading.setFont(new Font("Tahoma", Font.BOLD, 40));
        heading.setForeground(new Color(212, 175, 55));
        add(heading);

        // Rules
        JLabel rules = new JLabel();
        rules.setBounds(150, 150, 1200, 300);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rules.setForeground(new Color(212, 175, 55));
        rules.setVerticalAlignment(SwingConstants.TOP);
        rules.setText(
                "<html>" +
                        "1. Participation in the quiz is free and open to all persons above 18 years old.<br><br>" +
                        "2. There are a total of 10 questions.<br><br>" +
                        "3. You only have 15 seconds to answer each question.<br><br>" +
                        "4. No cell phones or other secondary devices in the room or test area.<br><br>" +
                        "5. No talking.<br><br>" +
                        "6. No one else can be in the room with you.<br><br>" +
                        "</html>"
        );
        add(rules);

        // Buttons
        back = new JButton("Back");
        back.setBounds(600, 600, 120, 35);
        back.setBackground(new Color(212, 175, 55));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        start = new JButton("Start");
        start.setBounds(800, 600, 120, 35);
        start.setBackground(new Color(212, 175, 55));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        // Background Image (add last so it doesn't cover UI components)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i = i1.getImage().getScaledInstance(1500, 780, Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 1500, 780);
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
