package com.example.gameofthrones;

public class LevelOne extends Level{
    private int numOfBlackSoldier;
    private int numOfRedSoldier;
    private int numOfTent;
    private final int maxRedSoldier=10;
    private final int maxNumOfTent=5;

    private void setSoldiers()
    {
        numOfRedSoldier= (int)(Math.random()*1000)%maxRedSoldier;
        numOfTent= (int) (Math.random()*1000)%maxNumOfTent;
        numOfBlackSoldier-=numOfRedSoldier;
        
        for (int gridRow= 0;gridRow<row;gridRow++)
            for(int girdCol =0;girdCol<col;girdCol++)
            {

            }
    }


}