package com.beesim.models;

public class abeilleCreation {
    private int positionX;
    private int positionY;
    private int capaciteNectar;
    public abeilleCreation(int positionX, int positionY, int capaciteNectar) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.capaciteNectar = capaciteNectar;
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
    public int getCapaciteNectar() {
        return capaciteNectar;
    }
    public void setCapaciteNectar(int capaciteNectar) {
        this.capaciteNectar = capaciteNectar;
    }
    

}
