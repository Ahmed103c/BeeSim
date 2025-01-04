package com.beesim.models;



public class Ruche {
    private int positionX;
    private int positionY;
    private int score ;
   

    public Ruche(int positionX, int positionY, int score) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.score = score;
    }
    public Ruche(int x, int y) {
        this.positionX = x;
        this.positionY = y;
      
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void ajouterNectar(int nectar) {
        this.score+=nectar;
    }
}