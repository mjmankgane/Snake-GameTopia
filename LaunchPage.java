import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LaunchPage extends JFrame implements ActionListener{

    JFrame frame = new JFrame();
    JButton myButton = new JButton("Enter Snake Island");
    JLabel myLabel = new JLabel("Welcome to Snake Island!");


    LaunchPage(){

        myButton.setBounds(100,160,200,40);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        myButton.setBackground(Color.GREEN);

        myLabel.setBounds(75,25,300,50);
        myLabel.setFont(new Font(null,Font.PLAIN,25));
        myLabel.setForeground(Color.RED);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(myButton);
        frame.add(myLabel);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==myButton) {
            frame.dispose();
            NewWindow myWindow = new NewWindow();
        }
    }
}
