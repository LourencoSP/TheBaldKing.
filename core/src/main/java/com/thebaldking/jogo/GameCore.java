package com.thebaldking.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameCore extends Game {
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    public SpriteBatch spriteBatch;

    private String playerName;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        spriteBatch = new SpriteBatch();

        // Inicia pela tela de início
        this.setScreen(new StartScreen(this));
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
        spriteBatch.dispose();
        super.dispose();
    }
}
