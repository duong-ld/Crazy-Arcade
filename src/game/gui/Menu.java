package game.gui;
 
import javax.swing.*;

import java.util.*;
import java.util.List;

import game.controller.Controller;

import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<JButton> buttons = new ArrayList<>();

    public Menu() {
        ImageIcon image = new ImageIcon("src/images/background/background.gif");

        setSize(image.getIconWidth(), image.getIconHeight());
        setTitle("GAME MENU");
        JLabel mainScreen = new JLabel(image);
        // mainScreen.setLayout(new BorderLayout());
        // setBounds(10, 10, 50, 30);

        buttons.add(new JButton("Play with Bot"));
        buttons.add(new JButton("Play with Human"));
        buttons.add(new JButton("Exit"));

        int width = getWidth();
        int height = getHeight();
        int buttonSizeWidth = 200;
        int buttonSizeHeight = 40;
        int lightHight = (int)(height-buttons.size()*buttonSizeHeight)/(buttons.size()+2);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBounds(
                (int)width/2-buttonSizeWidth/2, 
                lightHight*(i+1)+buttonSizeHeight*i, 
                buttonSizeWidth, 
                buttonSizeHeight
            );
            buttons.get(i).addActionListener(this);
            // buttons.get(i).setOpaque(false);
            // buttons.get(i).setContentAreaFilled(false);
            buttons.get(i).setBackground(new Color(128, 128, 128, 150));
            buttons.get(i).setFont(new Font("Dialog", Font.PLAIN, 18));
            // buttons.get(i).setForeground(Color.GRAY);
            mainScreen.add(buttons.get(i));
        }

        add(mainScreen);
        // add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        switch(e.getActionCommand()) {
            case "Play with Bot": {
                new Controller().getPlayers().remove(1);
                dispose();
                break;
            }
            case "Play with Human": {
                new Controller();
                dispose();
                break;
            }
            case "Exit": {
                dispose();
                System.exit(0);
            }
            case "Sound: ON": {
                // turn on the sound
                buttons.get(2).setText("Sound: OFF");
                break;
            }
            case "Sound: OFF": {
                // turn off the sound
                buttons.get(2).setText("Sound: ON");
                break;
            }
        }
    }

}
