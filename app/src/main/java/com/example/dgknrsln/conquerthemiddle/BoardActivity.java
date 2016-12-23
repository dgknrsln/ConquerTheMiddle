package com.example.dgknrsln.conquerthemiddle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BoardActivity extends AppCompatActivity {

    EditText etMove;
    TextView tvBoard;
    char[][] gameBoard;
    String board = "";
    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        getSupportActionBar().hide();

        etMove = (EditText)findViewById(R.id.editText);
        tvBoard = (TextView)findViewById(R.id.textView3);

        rand = new Random();

        gameBoard = new char[6][6];
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                gameBoard[i][j]='B';
            }
        }

        printBoard(gameBoard);

    }

    public void move(View v){

        String console = etMove.getText().toString();

        if(checkGameStatus(gameBoard)){
            printBoard(gameBoard);

            playerX(console,gameBoard);

        }
    printBoard(gameBoard);
        if(!checkGameStatus(gameBoard)){
            for(int i=0; i<6; i++){
                for(int j=0; j<6; j++){
                    gameBoard[i][j]='B';
                }
            }
            printBoard(gameBoard);
        }

        etMove.setText("");

    }

    public void printBoard (char[][] gameBoard){
        board = "";
        for (int i=0; i<6; i++) {
            for (int j=0; j<6; j++) {
                board = board + gameBoard[i][j] + "     ";
            }
            board = board + "\n";
        }
        tvBoard.setText(board);
    }

    public boolean checkGameStatus(char[][] gameBoard){
        boolean status = true;
        int countX_row = 0, countX_col = 0;
        int countO_row = 0, countO_col = 0;

        for (int i=0; i<6; i++) {
            for (int j=0; j<6; j++) {
                if (gameBoard[i][j]=='X')
                    countX_row++;
                else if (gameBoard[i][j]=='O')
                    countO_row++;
                if (gameBoard[j][i]=='X')
                    countX_col++;
                else if (gameBoard[j][i]=='O')
                    countO_col++;
            }

            if (countX_row==6 || countX_col==6) {
                Toast.makeText(getApplicationContext(),"You Won!",Toast.LENGTH_LONG).show();
                status = false;
            }

            else if (countO_row==6 || countO_col==6) {
                Toast.makeText(getApplicationContext(),"You Lose!",Toast.LENGTH_LONG).show();
                status = false;
            }

            countO_row=0; countO_col=0; countX_row=0; countX_col=0;
        }
        return status;
    }

    public void playerX(String console, char[][]gameBoard) throws ArrayIndexOutOfBoundsException{
        String userMove = console.substring(0,1);
        int x1 = 6, y1 = 6, x2 = 6, y2 = 6;

        String sub1 = console.substring(2,3);
        if(sub1.equals("0")||sub1.equals("1")||sub1.equals("2")||sub1.equals("3")||sub1.equals("4")||sub1.equals("5")){
            x1 = Integer.parseInt(sub1);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
        }

        String sub2 = console.substring(4,5);
        if(sub2.equals("0")||sub2.equals("1")||sub2.equals("2")||sub2.equals("3")||sub2.equals("4")||sub2.equals("5")){
            y1 = Integer.parseInt(sub2);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
        }



        if(userMove.equals("C")){
            if(x1<=5 && y1<=5 && x1*y1%5==0 && gameBoard[x1][y1]=='B'){
                gameBoard[x1][y1]='X';
                playerO(rand,gameBoard);
            }else{
                Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
            }
        }else if(userMove.equals("M")){
            String sub3 = console.substring(6,7);
            if(sub3.equals("0")||sub3.equals("1")||sub3.equals("2")||sub3.equals("3")||sub3.equals("4")||sub3.equals("5")){
                x2 = Integer.parseInt(sub3);
            }else{
                Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
            }

            String sub4 = console.substring(8,9);
            if(sub4.equals("0")||sub4.equals("1")||sub4.equals("2")||sub4.equals("3")||sub4.equals("4")||sub4.equals("5")){
                y2 = Integer.parseInt(sub4);
            }else{
                Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
            }
            boolean test1 = (x1==x2 && ((y1==0&& y2==5) || (y1==5 && y2==0))) || ((x1==5 && x2==0) || ((x1==0 && x2==5) && y1==y2));
            boolean test2 = x1<=5 && y1<=5 && gameBoard[x1][y1]=='X';

            if (test1 && test2) {
                makeTheMove(x1, y1, x2, y2, gameBoard);
                playerO(rand, gameBoard);
            }else {
                Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Invalid Move! Try Again.",Toast.LENGTH_LONG).show();
        }
    }

    public void playerO(Random rand, char[][]gameBoard){
        int moveType = rand.nextInt(2);
        int x1 = rand.nextInt(6);

        int y1 = rand.nextInt(6);

        if (moveType==0) {
            if (x1*y1%5==0 && gameBoard[x1][y1]=='B')
                gameBoard[x1][y1]='O';
            else
                playerO(rand, gameBoard);
        }

        else if (moveType==1) {
            int x2=rand.nextInt(6);
            int y2=rand.nextInt(6);
            boolean test1 = (x1==x2 && ((y1==0 && y2==5) || (y1==5 && y2==0))) || ((x1==5 && x2==0) || ((x1==0 && x2==5) && y1==y2));
            boolean test2 = gameBoard[x1][y1]=='O';
            if (test1 && test2)
                makeTheMove(x1, y1, x2, y2, gameBoard);
            else
                playerO(rand, gameBoard);
        }
    }

    public void makeTheMove(int x1, int y1, int x2, int y2, char[][] gameBoard){
        char temp = gameBoard[x1][y1];

        if (x1==x2 && y1>y2) {
            for (int i=4; i>=0; i--)
                gameBoard[x1][i+1]=gameBoard[x1][i];
            gameBoard[x1][0]=temp;
        }

        else if (x1==x2 && y2>y1) {
            for (int i=0; i<=4; i++)
                gameBoard[x1][i]=gameBoard[x1][i+1];
            gameBoard[x1][5]=temp;
        }

        else if (y1==y2 && x1>x2) {
            for (int i=4; i>=0; i--)
                gameBoard[i+1][y1]=gameBoard[i][y1];
            gameBoard[0][y2]=temp;
        }

        else if (y1==y2 && x2>x1) {
            for (int i=0; i<=4; i++)
                gameBoard[i][y1]=gameBoard[i+1][y1];
            gameBoard[5][y2]=temp;
        }
    }

}

