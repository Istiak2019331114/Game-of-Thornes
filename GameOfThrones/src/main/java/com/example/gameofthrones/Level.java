package com.example.gameofthrones;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane ;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public  class Level implements Initializable {

    @FXML
    public AnchorPane maze;
    private int screenWidth = 1080;
    private int screenHeight = 700;
    public int totalSoldier=0;
    public int row = 7;
    public int col = 12;

    public int rectangleWidth = screenWidth/col;
    public int rectangleHeight = screenHeight/row;

    private int playerimageViewRow=3;
    private  int playerimageViewCol=0;
    private int numOfTextFile=1;
    private TranslateTransition translate;
    private ImageView playerimageView;
    // Row Column based
    public int[][] isPath;
    // Row Column based

    private int[][] visited = new int[row][col];
    // Row Column based
    public GameElement[][] gameElements = new GameElement[row][col];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();

        addPlayerToMaze();

        maze.setPrefHeight(screenHeight);
        maze.setPrefWidth(screenWidth);

        addAnimationToPlayer();

        addBrickToMaze();
    }

    void init() {
        isPath = new int[row][col];

        FileReader reader;

        int random=((int)(Math.random()*1000))%numOfTextFile;

        String path = "src\\main\\resources\\com\\example\\gameofthrones\\path"+ Integer.toString(random)+".txt";

        try
        {
            reader = new FileReader(path);
            MakePath makePath = new MakePath(isPath,reader,row,col);
            calculateSoldiers();
            System.out.println(totalSoldier);

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        for (int gridRow = 0; gridRow < row; gridRow++)
            for (int gridCol = 0; gridCol < col; gridCol++) {
                if(isPath[gridRow][gridCol]==1) visited[gridRow][gridCol]=0;
                else visited[gridRow][gridCol] = 1;
            }
    }
    private void calculateSoldiers()
    {
        for(int i=0;i<row;i++)
            for (int j=0;j<col;j++) if(isPath[i][j]==1) totalSoldier++;
        totalSoldier--;
    }
    private void addPlayerToMaze()
    {
        Player player = new Player(playerimageViewCol * rectangleWidth, playerimageViewRow * rectangleHeight, rectangleHeight, rectangleWidth, maze,0);
        playerimageView = player.getImageView();
        playerimageView.setSmooth(true);
    }
    private void addAnimationToPlayer()
    {
        translate = new TranslateTransition();
        translate.setNode(playerimageView);
        translate.setDuration(Duration.millis(250));
    }

    private void addBrickToMaze()
    {
        int gridRow, gridCol;

        for (int i = 0; i < screenHeight; i += rectangleHeight)
            for (int j = 0; j < screenWidth; j += rectangleWidth) {
                gridRow = i / rectangleHeight;
                gridCol = j / rectangleWidth;

                if (isPath[gridRow][gridCol] == 0) {
                    Brick brick = new Brick(j, i, rectangleHeight,rectangleWidth,maze);
                    gameElements[gridRow][gridCol]= brick;
                }

            }
    }

    // Moving Main Character
    public void moveUp()
    {
        if(playerimageViewRow-1 >=0 && visited[playerimageViewRow-1][playerimageViewCol]==0)
        {
            playerimageViewRow-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(-rectangleHeight);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveDown()
    {
        if(playerimageViewRow+1<row && visited[playerimageViewRow+1][playerimageViewCol]==0)
        {
            playerimageViewRow+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(rectangleHeight);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveRight()
    {
        if(playerimageViewCol+1<col && visited[playerimageViewRow][playerimageViewCol+1]==0)
        {
            playerimageViewCol+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(rectangleWidth);
            translate.setByY(0);
            translate.play();
        }
    }
    public void moveLeft()
    {
        if(playerimageViewCol-1 >=0 && visited[playerimageViewRow][playerimageViewCol-1]==0)
        {
            playerimageViewCol-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
            translate.play();
        }
    }


}