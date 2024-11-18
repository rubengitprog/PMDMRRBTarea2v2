package com.example.pmdmrrbtarea2v2;


public class GameData {
    private final String title;
    private final String description;
    private final int image;
    private final String habilidad;
    private final int vida;


    public GameData(String title, String description, int image, String habilidad, int vida) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.habilidad = habilidad;
        this.vida = vida;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public int getVida() {
        return vida;
    }

    public String getDescription() {
        return description;
    }

}
