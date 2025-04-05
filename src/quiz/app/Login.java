package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.SystemColor.text;

public class Login extends JFrame implements ActionListener {

    JTextField text;
    JButton Next, back;
    Login() {
        getContentPane().setBackground(new Color(0, 31, 63)); // Steel Blue

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/1.jpg"));
//      Image i = i1.getImage().getScaledInstance(550,550,Image.SCALE_DEFAULT);
        Image i = i1.getImage().getScaledInstance(840, 750, Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(650, 0, 840, 750);
        add(image);

        JLabel heading = new JLabel("QUIZ TEST");  // Fixed syntax error
        heading.setBounds(185, 100, 400, 45);
        heading.setFont(new Font("Tahoma", Font.BOLD, 50)); // Set font size and style
        heading.setForeground(Color.WHITE); // Set text color
        add(heading); // Add to JFrame

        JLabel name = new JLabel("Enter Your Name");
        name.setBounds(160, 230, 400, 50);
        name.setFont(new Font("Tahoma", Font.BOLD,40));
        name.setForeground(new Color(212, 175, 55));
        add(name);

//        the below content is for building the frame below ENter your name
        text = new JTextField();
        text.setBounds(75, 340, 500, 45);
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(text);

        Next = new JButton("Next");
        Next.setBounds(140,500,120,35);
        Next.setBackground(new Color(212, 175, 55));
        Next.setForeground(Color.WHITE);
        Next.addActionListener(this);
        add(Next);

        back = new JButton("back");
        back.setBounds(400, 500, 120, 35);
        back.setBackground(new Color(212, 175, 55));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);


// it sets the width and height of the JFrame
      setSize(1500, 780);
//      sets the location from x and y axis of the JFrame
        setLocation(15, 20);
        setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Next){

            String name = text.getText();
            setVisible(false);
            new Rules(name);
        } else if (e.getSource() == back){
            System.exit(50);
        }
    }

    public static void main(String[] args){
        new Login();
    }
}
