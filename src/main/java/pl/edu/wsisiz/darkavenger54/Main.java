package pl.edu.wsisiz.darkavenger54;

import pl.edu.wsisiz.darkavenger54.frames.GameStartGUIFrame;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("System Look and Feel Not Supported");
        }
        GameStartGUIFrame frame = new GameStartGUIFrame();
        frame.setVisible(true);
    }
}