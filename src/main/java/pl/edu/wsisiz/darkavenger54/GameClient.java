package pl.edu.wsisiz.darkavenger54;

import pl.edu.wsisiz.darkavenger54.frames.GameGUIFrame;
import pl.edu.wsisiz.darkavenger54.frames.GameInviteDialog;
import pl.edu.wsisiz.darkavenger54.frames.GameStartGUIFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient implements Runnable
{
    private String name;
    private Socket socket;
    private final String HOST = "localhost";
    private BufferedReader in;
    private PrintWriter out;
    private GameGUIFrame gameGUIFrame;
    private boolean isConnected = false;
    private GameStartGUIFrame startGUIFrame;


    public GameClient() {}

    public void connect(int PORT) throws IOException
    {
        socket = new Socket(HOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        isConnected = true;
        new Thread(this).start();
    }

    public void sendMessage(String message)
    {
        sendEvent(EnEventType.MESSAGE, message);
    }
    public void disconnect()
    {
        if (isConnected)
        {
            sendEvent(EnEventType.DISCONNECT, "");
            isConnected = false;
        }
        try
        {
            socket.close();
            if (gameGUIFrame != null) {
                gameGUIFrame.dispose();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void askForGame()
    {
        sendEvent(EnEventType.ASK_FOR_GAME, "");
    }

    public void setGameGUIFrame(GameGUIFrame gameGUIFrame)
    {
        this.gameGUIFrame = gameGUIFrame;
    }

    @Override
    public void run()
    {
        try
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
            {
                String[] parts = inputLine.split(" ", 2);
                if (parts.length == 0) continue;

                String eventTypeStr = parts[0];
                String eventData = parts.length > 1 ? parts[1] : "";

                EnEventType eventType;
                try
                {
                    eventType = EnEventType.valueOf(eventTypeStr.trim());
                } catch (IllegalArgumentException ex)
                {
                    System.out.println("Unknown event type: " + eventTypeStr);
                    continue;
                }
                System.out.println("Received: " + inputLine);
                switch (eventType)
                {
                    case MESSAGE:
                        SwingUtilities.invokeLater(() ->
                        {
                            gameGUIFrame.getTextArea1().append(eventData + "\n");
                        });
                        break;
                    case SET_NAME:
                        this.name = eventData;
                        SwingUtilities.invokeLater(() -> {
                            if (gameGUIFrame != null) {
                                gameGUIFrame.setName(name);
                                gameGUIFrame.updateGUI();
                            }
                        });
                        break;
                    case DISCONNECT:
                        SwingUtilities.invokeLater(() ->
                        {
                            gameGUIFrame.getTextArea1().setText("Other Player Disconnected\n");
                            gameGUIFrame.disableAllButtons();
                            gameGUIFrame.setStatusLabel("");
                            gameGUIFrame.disableStartNewGameButton();
                            gameGUIFrame.disableSendMsgButton();
                        });
                        break;
                    case ASK_FOR_GAME:
                        GameInviteDialog dialog = new GameInviteDialog(gameGUIFrame);
                        dialog.setModal(true);
                        dialog.setVisible(true);
                        if(dialog.isConfirmed())
                        {
                            sendEvent(EnEventType.CONFIRMED, "");
                        }
                        else
                        {
                            sendEvent(EnEventType.CANCELED, "");
                        }
                        dialog.dispose();
                        break;
                    case CANCELED:
                        JOptionPane.showMessageDialog(null, "Game canceled", "Game canceled", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case MOVE:
                        String[] moveParts = eventData.split(" ");
                        if (moveParts.length == 2) {
                            int index = Integer.parseInt(moveParts[0]);
                            EnFigureType figure = EnFigureType.valueOf(moveParts[1]);
                            SwingUtilities.invokeLater(() -> gameGUIFrame.setMoveOnButton(index, figure));
                        }
                        break;
                    case CONNECT:
                        SwingUtilities.invokeLater(() -> {
                            if (!"".equals(eventData) && gameGUIFrame != null) {
                                gameGUIFrame.getTextArea1().append(eventData + "\n");
                            }

                            if (gameGUIFrame != null) {
                                gameGUIFrame.enableStartNewGameButton();
                                gameGUIFrame.enableSendMsgButton();
                            }
                        });
                        break;
                    case SERVER_STOPPED:
                        isConnected = false;
                        disconnect();
                        gameGUIFrame.dispose();
                        JOptionPane.showMessageDialog(null, "Server stopped", "Server stopped", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case START_GAME:
                        SwingUtilities.invokeLater(() -> {
                            gameGUIFrame.clearBoard();
                            if(EnFigureType.CROSS.toString().equals(eventData))
                            {
                                gameGUIFrame.setFigureLabel(EnFigureType.CROSS);
                            }
                            else
                            {
                                gameGUIFrame.setFigureLabel(EnFigureType.CIRCLE);
                            }
                        });
                        break;
                    case YOUR_TURN:
                        SwingUtilities.invokeLater(() ->
                        {
                            gameGUIFrame.setButtonsEnabled(true);
                            gameGUIFrame.setStatusLabel("Your turn...");
                        });
                        break;
                    case WAITING_FOR_OPPONENT:
                        SwingUtilities.invokeLater(() ->
                        {
                            gameGUIFrame.setButtonsEnabled(false);
                            gameGUIFrame.setStatusLabel("Waiting for opponent...");
                        });
                        break;
                    case WIN:
                        SwingUtilities.invokeLater(() -> {
                            gameGUIFrame.setButtonsEnabled(false);
                            gameGUIFrame.disableAllButtons();
                            gameGUIFrame.setStatusLabel("YOU WIN!!!");
                        });
                        break;
                    case LOSE:
                        SwingUtilities.invokeLater(() -> {
                            gameGUIFrame.setButtonsEnabled(false);
                            gameGUIFrame.disableAllButtons();
                            gameGUIFrame.setStatusLabel("YOU LOSE!!!");
                        });
                        break;
                    case DRAW:
                        SwingUtilities.invokeLater(() -> {
                            gameGUIFrame.setButtonsEnabled(false);
                            gameGUIFrame.disableAllButtons();
                            gameGUIFrame.setStatusLabel("Draw");
                        });
                        break;
                    case SERVER_FULL:
                        JOptionPane.showMessageDialog(null, "Server is full. Try again later.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                        disconnect();
                        break;
                    default:
                        System.out.println("Unhandled event type: " + eventType);
                        break;
                }
            }
        } catch (IOException e)
        {
            System.out.println("Disconnected from server: " + e.getMessage());
        }
    }

    public void sendEvent(EnEventType eventType, String eventData)
    {
        out.println(eventType.getEventType() + eventData);
    }
    public void setStartGUIFrame(GameStartGUIFrame startGUIFrame) {
        this.startGUIFrame = startGUIFrame;
    }
}
