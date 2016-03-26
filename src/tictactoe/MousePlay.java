/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author suyog
 */
public class MousePlay {
    static class panel implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Object source = me.getSource();
            TicTacToe.Panel panel = (TicTacToe.Panel) source;
            if(panel.getBackground() != Color.green && panel.getBackground() != Color.red){
                TicTacToe.turn++;
                if(TicTacToe.turn % 2 != 0){
                    panel.setBackground(Color.red);
                    panel.color = panel.getBackground();
                }else{
                    panel.setBackground(Color.green);
                    panel.color = panel.getBackground();
                }
            }
            int winner = func.win(func.check());
            if(winner > 0 || TicTacToe.turn == 9){
                func.GameOver(winner);
            }
            TicTacToe.mainFrame.validate();
            int[][] arr = func.check();
            func.comChose(arr);
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Object source = me.getSource();
            TicTacToe.Panel panel = (TicTacToe.Panel) source;
            if(panel.getBackground() != Color.green && panel.getBackground() != Color.red){
                panel.setBackground(Color.yellow);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Object source = me.getSource();
            TicTacToe.Panel panel = (TicTacToe.Panel) source;
            panel.setBackground(panel.color);
        }
        
    }
    private static class func{
        private static int[][] check(){
            /*
                Check which grids are green and which are red. Return and Danger grids
            */
            int counter = 0;
            int[][] z = new int[3][3];
            for(int i = 0; i < 3; i++){
                for(int x = 0; x < 3; x++){
                    TicTacToe.Panel s = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(counter);
                    if(s.getBackground() == Color.red){
                        z[i][x] = 1;
                    }else if(s.getBackground() == Color.green){
                        z[i][x] = 2;
                    }else{
                        z[i][x] = 0;
                    }
                    counter++;
                }

            }
            return z;
        }
        private static int win(int[][] arr){
            int p1_left_diag = 0;
            int p2_left_diag = 0;
            for(int i = 0; i < 3; i++){
                int p1_counter_vertical = 0;
                int p2_counter_vertical = 0;
                int p1_counter_horizontal = 0;
                int p2_counter_horizontal = 0;
                for(int x = 0; x < 3; x++){
                    if(arr[i][x] == 1){
                        p1_counter_horizontal++;
                    }else if(arr[i][x] == 2){
                        p2_counter_horizontal++;
                    }
                    if(arr[x][i] == 1){
                        p1_counter_vertical++;
                    }else if(arr[x][i] == 2){
                        p2_counter_vertical++;
                    }
                    if(x == i){
                        if(arr[x][i] == 1){
                            p1_left_diag++;
                        }else if(arr[x][i] == 2){
                            p2_left_diag++;
                        }
                    }
                }
		//this so for the last diagonals
                if(p1_counter_horizontal == 3 || p1_counter_vertical == 3 || p1_left_diag == 3){
                    System.out.println("RED WINS");
                    return 1;
                }else if(p2_counter_horizontal == 3 || p2_counter_vertical == 3 || p2_left_diag == 3){
                    System.out.println("GREEN WINS");
                    return 2;
                }
                //for the diagonal on the other side
                if(arr[2][0] == arr[1][1] && arr[0][2] == arr[1][1]){
                    return arr[1][1];
                }
            }
            return 0;
        }
        private static void GameOver(int winner){
            TicTacToe.mainFrame.getContentPane().removeAll();
            TicTacToe.mainFrame.getContentPane().repaint();
            JPanel p = TicTacToe.whoWon;
            JTextField t = new JTextField();
            switch (winner) {
                case 1:
                    t.setText("Player One Won!!!!!");
                    break;
                case 2:
                    t.setText("Player Two Won!!!!!");
                    break;
                default:
                    t.setText("This Game was a tie!!!");
                    break;
            }
            t.setFont(new Font("Engravers MT", Font.BOLD, 23));
            t.setEditable(false);
            p.add(t);
            TicTacToe.mainFrame.add(p);
        }
        //computer decides to play on this one
        private static void comChose(int[][] arr){
            /*if (closeWin() > 0) {
                JPanel p = (JPanel) TicTacToe.mainFrame.getContentPane().getComponent(closeWin());
                p.setBackground(Color.green);
                TicTacToe.turn++;
            }
            */
            TicTacToe.Panel p;
            if(TicTacToe.turn == 1){
                if(arr[1][1] == 0){
                    p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(4);
                    p.setBackground(Color.green);
                    p.color = p.getBackground();
                    TicTacToe.turn++;
                }else {
                    //pick the corners
                    Random r = new Random();
                    int rand = r.nextInt(4) + 1;
                    switch (rand){
                        case 1:
                            p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(0);
                            break;
                        case 2:
                            p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(2);
                            break;
                        case 3:
                            p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(6);
                            break;
                        case 4:
                            p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(8);
                            break;
                        default:
                            System.out.println("I am sorry but this program seems to have crashed!!!!");
                            p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(5);
                    }
                    p.setBackground(Color.green);
                    p.color = p.getBackground();
                    TicTacToe.turn++;
                }
            }else {
                if (closeWin() == 0){
                }
            }
        }
        //to help the computer play, this will tell the computer which way the opp is about to win
        private static int closeWin(){
            int counter = 0;
            for (int i = 0; i < 3; i++) {
                for (int z = 0; z < 3; z++) {
                    TicTacToe.Panel p = (TicTacToe.Panel) TicTacToe.mainFrame.getContentPane().getComponent(counter);
                    counter++;
                    if(z%2 == 0){
                    }
                }
            }
            return 0;
        }
    }
}
