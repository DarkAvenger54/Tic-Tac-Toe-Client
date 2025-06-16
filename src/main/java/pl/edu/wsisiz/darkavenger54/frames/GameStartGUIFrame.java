/*
 * Created by JFormDesigner on Fri Jun 13 19:41:58 CEST 2025
 */

package pl.edu.wsisiz.darkavenger54.frames;

import pl.edu.wsisiz.darkavenger54.GameClient;
import pl.edu.wsisiz.darkavenger54.Utility;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Zhenia
 */
public class GameStartGUIFrame extends JFrame {
    private GameClient gameClient;
    private GameGUIFrame gameGUIFrame;
    public GameStartGUIFrame() {
        this.gameClient = new GameClient();
        gameClient.setStartGUIFrame(this);
        initComponents();
        setResizable(false);
    }

    private void connect(ActionEvent e) {
        try
        {
            if(Utility.tryParsePort(portTextField.getText()))
            {
                gameClient.setStartGUIFrame(this);
                gameClient.connect(Integer.parseInt(portTextField.getText()));


                gameGUIFrame = new GameGUIFrame(this, gameClient);
                gameClient.setGameGUIFrame(gameGUIFrame);
                gameGUIFrame.setVisible(true);
                this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid port");
            }
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Invalid port");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        portTextField = new JTextField();
        connectButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- connectButton ----
            connectButton.setText("Connect");
            connectButton.addActionListener(e -> connect(e));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(portTextField)
                            .addComponent(connectButton, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(connectButton)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JTextField portTextField;
    private JButton connectButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
