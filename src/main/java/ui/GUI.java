package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import mancala.AyoRules;
import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.KalahRules;
import mancala.MancalaGame;
import mancala.Player;
import mancala.Saver;
import mancala.UserProfile;

public class GUI {
    private JFrame frame;
    private Saver save = new Saver();
    JLabel store1;
    JLabel store2;
    JLabel playerTurn;
    PositionAwareButton[][] buttons;

    private MancalaGame game;
    private Player playerOne;
    private Player playerTwo;

    private static final int tall = 2;
    private static final int wide = 6;
    private int gameMode;

    public GUI() {
        game = new MancalaGame();
        showAyoOrKal();
        initializeGame();
        
        System.out.println(playerOne.getUserProfile().getKalahGames());
        System.out.println(playerTwo.getUserProfile().getKalahLosses());
        System.out.println(gameMode);
        
        initializeFrame();

        frame.add(setupBoardPanel(), BorderLayout.CENTER);
        frame.add(setupMenuPanel(), BorderLayout.EAST);
        finalizeFrameSettings();
    }

    private void initializeGame() {
        // game.startNewGame();
        playerOne = new Player("Player 1");
        playerTwo = new Player("Player 2");
        store1 = new JLabel("Store 1: 0");
        store2 = new JLabel("Store 2: 0");
        game.setPlayers(playerOne, playerTwo);
        for (int i = 1; i <= 2; i++) {
            showInitialOptions(i);
        }
    }

    private void initializeFrame() {
        frame = new JFrame("Mancala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Set layout for the frame
    }

    private void showAyoOrKal() {
        // Create an array of two buttons
        Object[] options = {"AyoAyo", "Kalah"};

        // Show the option dialog with two buttons
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option:",
                "Game Mode",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        // Process the user's choice
        if (choice == 0) {
            game.setBoard(new AyoRules());
        } else if (choice == 1) {
            game.setBoard(new KalahRules());
        }
        game.getBoard().setMode(game.getBoard());
        gameMode = game.getBoard().gameMode();
    }

    private void showInitialOptions(int playerNum) {
        Object[] options = {"Login", "Register"};
        int choice = JOptionPane.showOptionDialog(null, "Welcome to Mancala!", "Mancala",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            loginUser(playerNum);
        } else if (choice == 1) {
            registerUser(playerNum);
        }
    }

    private void registerUser(int playerNum) {
        Player currentPlayer = playerNum == 1 ? playerOne : playerTwo;
        JTextField username = new JTextField();
        promptPlayer(currentPlayer, "Register Player " + playerNum, username, true);
    }

    private void loginUser(int playerNum) {
        JTextField username = new JTextField();
        Player currentPlayer = playerNum == 1 ? playerOne : playerTwo;
        promptPlayer(currentPlayer, "Login Player " + playerNum, username, false);

        UserProfile userProfile = loadUserProfile(username.getText());

        if (userProfile != null) {

            currentPlayer.setName(username.getText());
            currentPlayer.setUserProfile(userProfile);
            // Proceed with the game using the loaded profile
        } else {
            // User profile not found, handle accordingly
            popUp(frame, "User profile not found for username: " + username);
        }
    }

    public void gamesPlayed(UserProfile user) {
        System.out.println("In games Played");
        if (gameMode == 1) {
            user.setAyoGames();
        } else {
            user.setKalahGames();
        }
    }

    public void gamesWon(UserProfile user) {
        System.out.println("In games won");
        if (gameMode == 1) {
            user.setAyoWins();
        } else {
            user.setKalahWins();
        }
    }

    public void gamesLost(UserProfile user) {
        System.out.println("In games lost");
        if (gameMode == 1) {
            user.setAyoLosses();
        } else {
            user.setKalahLosses();
        }
    }

    private UserProfile loadUserProfile(String username) {
        String filePath = "assets/" + username + ".ser"; // Adjust the path based on your project structure

        try{
            return (UserProfile) save.loadObject(filePath);
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions, e.g., if the file doesn't exist or cannot be deserialized
            popUp(frame, "Profile Does Not Exist. Playing As Unregistered Player.");
            return null;
        }
    }

    private void promptPlayer(Player curPlayer, String text, JTextField usernameField, boolean register) {
        Object[] message = {
            "Username:", usernameField
        };

        int option = JOptionPane.showConfirmDialog(null, message, text, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            curPlayer.setName(usernameField.getText());
            if (register) {
                UserProfile newUser = new UserProfile();
                newUser.setName(usernameField.getText());
                saveUserProfile(newUser);
            }
            // Process information
        }
    }

    private void saveUserProfile(UserProfile userProfile) {
        try {
            save.saveObject(userProfile, "assets/" + userProfile.getName() + ".ser");
        } catch (IOException i) {
            popUp(frame, "Could Not Save Profile. Playing As Unregistered Player.");
        }
    }

    private JPanel makeButtonGrid(int tall, int wide){ 
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(tall, wide));
        
        for (int y = 0; y < tall; y++) {
            for (int x = 0; x < wide; x++) {
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setText(generateButtonText(y, x)); // Set initial text
                buttons[y][x].setAcross(x + 1);
                buttons[y][x].setDown(y + 1);
                buttons[y][x].addActionListener(e -> handlePitChoice(e, tall, wide));
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    private String generateStoreText(int storeNum, Player storeNeeded) {
        return "Store " + storeNum + ": " + game.getStoreCount(storeNeeded);
    }
    
    private void playGame(int posY, int posX) {
        try {
                game.getBoard().moveStones(getButtonPosition(posY, posX), game.getCurrentPlayer().getPlayerNum());
                game.setCurrentPlayer(game.getCurrentPlayer() == playerOne ? playerTwo : playerOne);
            } catch (InvalidMoveException e) {
                popUp(frame, e.getMessage());
            }
    }

    public static void popUp(JFrame frame, String message) {
        final JDialog dialog = new JDialog(frame, message, true);
        dialog.add(new JLabel(message, JLabel.CENTER));
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(frame); // Center on frame

        dialog.setVisible(true); // Make the dialog visible
    }

    private String generateButtonText(int row, int col) {
        int position = getButtonPosition(row + 1, col + 1);
        return "<html>Pit " + position + "<br/>Stones: " + game.getNumStones(getButtonPosition(row + 1, col + 1)) + "</html>";
    }

    private int getButtonPosition(int row, int col) {
        if (row == 2) {
            return col; // For row 2, positions are sequential from 1 to 6
        } else if (row == 1) {
            return 13 - col; // For row 1, positions are in reverse from 12 to 7
        }
        return -1; // In case of invalid input
    }

    private JPanel setupBoardPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Label at the top
        JLabel title = new JLabel("Mancala Board", JLabel.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);
        playerTurn = new JLabel(game.getCurrentPlayer().getName(), JLabel.CENTER);
        mainPanel.add(playerTurn, BorderLayout.SOUTH);
        JPanel board = makeButtonGrid(tall, wide); 
        // Set the combined border to the board
        mainPanel.setBorder(commonBorder());

        store1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        mainPanel.add(store1, BorderLayout.EAST);
        store2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        mainPanel.add(store2, BorderLayout.WEST);

        // Add the board to the center of the main panel
        mainPanel.add(board, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel setupMenuPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Label at the top
        JLabel title = new JLabel("Menu", JLabel.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel fileChooserPanel = new JPanel();
        
        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.addActionListener(e -> handleFileChooser());
        
        fileChooserPanel.add(chooseFileButton);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton exit = new JButton("Exit Game");
        exit.addActionListener(e -> {
            resetGame();
        });

        JButton saveGame = new JButton("Save Game");
        saveGame.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog("Enter A Filename: ");
            if (filename.length() == 0) filename = "Untitled Game";

            try {
                System.out.println(game);
                save.saveObject(game, "assets/" + filename + ".ser");
            } catch (IOException error) {
                System.out.println(game);
                popUp(frame, "Failed To Save Game");
            }
        });

        buttonsPanel.add(saveGame);
        buttonsPanel.add(exit);

        mainPanel.add(fileChooserPanel);
        mainPanel.add(buttonsPanel);
        mainPanel.setBorder(commonBorder());

        return mainPanel;
    }

    private CompoundBorder commonBorder() {
        // Create a black line border
        Border blackLine = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black);
    
        // Create an empty border for top and bottom padding
        Border verticalPadding = BorderFactory.createEmptyBorder(20, 10, 10, 10);
    
        // First, apply vertical padding
        return new CompoundBorder(verticalPadding, blackLine);
    }

    private void finalizeFrameSettings() {
        frame.pack(); // Pack the frame after all components are added
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);
    }

    public void handleFileChooser() {
        JFileChooser fileChooser = new JFileChooser("assets");

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            String projectDirectory = "C:\\Users\\ashar\\Learning Java\\GP3";
            Path absolutePathObject = Paths.get(absolutePath);
            Path projectDirectoryObject = Paths.get(projectDirectory);
            // Calculate the relative path
            Path relativePath = projectDirectoryObject.relativize(absolutePathObject);
            // Convert the Path back to a String
            String selectedFile = relativePath.toString().replace("\\", "/");

            try {
                game = (MancalaGame) save.loadObject(selectedFile);
                playerOne = game.getPlayers().get(0);
                playerTwo = game.getPlayers().get(1);
                setGameData(tall, wide);
                generateStoreText(1, playerOne);
                generateStoreText(2, playerTwo);
                popUp(frame, "Loaded successfully");
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                popUp(frame, "Error Loading Game File");
            }
        }
    }

    public void handlePitChoice(ActionEvent e, int tall, int wide) {
        PositionAwareButton clickedButton = (PositionAwareButton) e.getSource();
        int posX = clickedButton.getAcross(); // Assuming getAcross() returns the X position
        int posY = clickedButton.getDown();   // Assuming getDown() returns the Y position
        
        playGame(posY, posX);
        setGameData(tall, wide);
        if (game.isGameOver()) {
            gamesPlayed(playerOne.getUserProfile());
            gamesPlayed(playerTwo.getUserProfile());
            try {
                if (game.getWinner().getName().equals("TIE_GAME")) {
                    popUp(frame, "It's A Tie!");
                } else {
                    Player winner = game.getWinner();
                    Player loser = winner.equals(playerOne) ? playerTwo : playerOne;
                    gamesLost(loser.getUserProfile());
                    gamesWon(winner.getUserProfile());
                    saveUserProfile(winner.getUserProfile());
                    saveUserProfile(loser.getUserProfile());
                    popUp(frame, "Congratulations " + winner.getName() + "! You Win!");
                }
                    resetGame();
                } catch (GameNotOverException error) {
                    popUp(frame, error.getMessage());
            }
        }
    }

    private void setGameData(int tall, int wide) {
        store1.setText(generateStoreText(1, playerOne));
        store2.setText(generateStoreText(2, playerTwo));
        playerTurn.setText(game.getCurrentPlayer().getName());
        for (int i = 0; i < tall; i++) {
            for (int j = 0; j < wide; j++) {
                buttons[i][j].setText(generateButtonText(i, j)); // Update text
            }
        }
    }

    private void resetGame() {
        game.startNewGame();
        game.setCurrentPlayer(playerOne);
        game.getBoard().registerPlayers(playerOne, playerTwo);
        setGameData(tall, wide);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
