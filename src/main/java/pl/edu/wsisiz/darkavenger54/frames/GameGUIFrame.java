package pl.edu.wsisiz.darkavenger54.frames;

import java.awt.event.*;

import pl.edu.wsisiz.darkavenger54.EnEventType;
import pl.edu.wsisiz.darkavenger54.EnFigureType;
import pl.edu.wsisiz.darkavenger54.GameClient;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Zhenia
 */
public class GameGUIFrame extends JFrame {
    private JButton[] buttons;
    private GameClient gameClient;
    private GameStartGUIFrame startGUIFrame;
    public GameGUIFrame(GameStartGUIFrame startGUIFrame, GameClient gameClient) {
        this.gameClient = gameClient;
        this.startGUIFrame = startGUIFrame;
        initComponents();
        buttons = new JButton[9];
        buttons[0] = button0;
        buttons[1] = button1;
        buttons[2] = button2;
        buttons[3] = button3;
        buttons[4] = button4;
        buttons[5] = button5;
        buttons[6] = button6;
        buttons[7] = button7;
        buttons[8] = button8;
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].addActionListener(e -> gameClient.sendEvent(EnEventType.MOVE, String.valueOf(index)));
        }
        disableAllButtons();
        statusLabel.setText("");
        textArea1.setEditable(false);
        label5.setText("");
        setResizable(false);
        startNewGameButton.setEnabled(false);
        sendMsgButton.setEnabled(false);
    }

    private void thisWindowClosed(WindowEvent e) {
        startGUIFrame.setVisible(true);
        gameClient.disconnect();
    }

    public void updateGUI()
    {
        this.repaint();
    }

    private void sendMsg(ActionEvent e) {
        gameClient.sendMessage(textField.getText());
    }


    public JTextArea getTextArea1()
    {
        return textArea1;
    }

    private void startNewGame(ActionEvent e) {
        gameClient.askForGame();
    }
    public void setMoveOnButton(int index, EnFigureType figure) {
        if (index >= 0 && index < buttons.length) {
            buttons[index].setText(figure == EnFigureType.CROSS ? "X" : "O");
            buttons[index].setEnabled(false);
        }
    }

    // Активировать только пустые кнопки
    public void setButtonsEnabled(boolean enabled) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().isEmpty()) {
                buttons[i].setEnabled(enabled);
            }
        }
    }

    // Блокировать все кнопки (после победы/ничьей)
    public void disableAllButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    public void enableStartNewGameButton()
    {
        startNewGameButton.setEnabled(true);
    }

    public void enableSendMsgButton()
    {
        sendMsgButton.setEnabled(true);
    }
    public void disableStartNewGameButton()
    {
        startNewGameButton.setEnabled(false);
    }
    public void disableSendMsgButton()
    {
        sendMsgButton.setEnabled(false);
    }
    public void clearBoard() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(false); // Деактивируем до YOUR_TURN
        }
    }

    public void setName(String name)
    {
        nameLabel.setText(name);
    }
    public void setStatusLabel(String status)
    {
        statusLabel.setText(status);
    }

    public void setFigureLabel(EnFigureType figure)
    {
        if(EnFigureType.CROSS == figure)
        {
            label5.setText("X");
        }
        else
        {
            label5.setText("O");
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        panel2 = new JPanel();
        button0 = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        panel3 = new JPanel();
        textField = new JTextField();
        sendMsgButton = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        panel4 = new JPanel();
        statusLabel = new JLabel();
        startNewGameButton = new JButton();
        panel5 = new JPanel();
        nameLabel = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            //======== panel2 ========
            {

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button6, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button0, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button7, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(10, Short.MAX_VALUE))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button0, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button3, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button6, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(button7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(11, Short.MAX_VALUE))
                );
            }

            //======== panel3 ========
            {

                //---- sendMsgButton ----
                sendMsgButton.setText("Send");
                sendMsgButton.addActionListener(e -> sendMsg(e));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textArea1);
                }

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane1)
                                .addGroup(panel3Layout.createSequentialGroup()
                                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sendMsgButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollPane1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(sendMsgButton))
                            .addContainerGap())
                );
            }

            //======== panel4 ========
            {

                //---- statusLabel ----
                statusLabel.setText("123456789012345678901234567890");

                //---- startNewGameButton ----
                startNewGameButton.setText("Start New Game");
                startNewGameButton.addActionListener(e -> startNewGame(e));

                //======== panel5 ========
                {

                    //---- nameLabel ----
                    nameLabel.setText("12345678901234567890");

                    //---- label3 ----
                    label3.setText("Your Name:");

                    //---- label4 ----
                    label4.setText("Your Figure:");

                    //---- label5 ----
                    label5.setText("1234567890");

                    GroupLayout panel5Layout = new GroupLayout(panel5);
                    panel5.setLayout(panel5Layout);
                    panel5Layout.setHorizontalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel5Layout.createParallelGroup()
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nameLabel))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(label4)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label5)))
                                .addContainerGap(10, Short.MAX_VALUE))
                    );
                    panel5Layout.setVerticalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label3)
                                    .addComponent(nameLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label4)
                                    .addComponent(label5))
                                .addContainerGap(26, Short.MAX_VALUE))
                    );
                }

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(startNewGameButton)
                                .addComponent(statusLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                            .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(143, 143, 143))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addComponent(statusLabel)
                                    .addGap(15, 15, 15)
                                    .addComponent(startNewGameButton)))
                            .addGap(0, 35, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(11, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JPanel panel2;
    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JPanel panel3;
    private JTextField textField;
    private JButton sendMsgButton;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel panel4;
    private JLabel statusLabel;
    private JButton startNewGameButton;
    private JPanel panel5;
    private JLabel nameLabel;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
