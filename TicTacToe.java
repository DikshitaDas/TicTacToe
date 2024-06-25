import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; //50px for text panel

    JFrame frame = new JFrame("TicTacToe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();


    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int times=0;
    /**
     * 
     */
    TicTacToe () {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white); //font color
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER); //align text center
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true); //set the background color to the entire text label

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel) ; //add the text label to the text panel
        frame.add(textPanel, BorderLayout.NORTH); //set the text panel to north of the frame


        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray); //create the game board as the same color
        frame.add(boardPanel, BorderLayout.CENTER);

        for(int r=0;r<3;r++) {
            for(int c=0;c<3;c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);  //assign the button to the board panel

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        
                        if(gameOver){
                            return;
                        }

                        if(tile.getText() == ""){
                        tile.setText(currentPlayer);
                        times++;
                        checkWinner();

                        if(!gameOver){     
                                currentPlayer = currentPlayer ==playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer+"'s Turn");
                             }                     
                        }
                    }
                });
                
            }
        }
    }
    void checkWinner() {
        //horizontal
        for(int r=0;r<3;r++) {
            if(board[r][0].getText()=="") {
                continue;
            }
            if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText()) {
                
                for(int i=0;i<3;i++){
                    setWinner(board[r][i]) ;
                }
                
                gameOver =true;
                return;
            }

        }

        //vertical
        for(int c=0;c<3;c++) {
            if(board[0][c].getText()==""){
                continue;
            }
            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText()) {
                  for(int i=0;i<3;i++){
                    setWinner(board[i][c]) ;
                }
                
                gameOver =true;
                return;
            }
        }

        //diagonal
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {

            for(int i=0;i<3;i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        
        //anti-diagonal

        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][0].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if(times==9) {
            for(int r=0;r<3;r++) {
                for(int c=0;c<3;c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver=true;
        }


    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the winner!");

    }

    void setTie(JButton tile) {
         tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}