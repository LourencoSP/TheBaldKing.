package com.thebaldking.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen implements Screen {
    private GameCore game;
    private SpriteBatch batch;
    private BitmapFont font;
    private String playerName;

    public StartScreen(GameCore game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = game.spriteBatch;
        font = game.font;
        playerName = "";
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Digite seu nome: " + playerName, 100, 400);
        font.draw(batch, "Pressione ENTER para iniciar", 100, 350);
        batch.end();

        // Captura letras (A-Z), espaço e backspace – para simplificação, estão listadas individualmente
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) playerName += "A";
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) playerName += "B";
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) playerName += "C";
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) playerName += "D";
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) playerName += "E";
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) playerName += "F";
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) playerName += "G";
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) playerName += "H";
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) playerName += "I";
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) playerName += "J";
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) playerName += "K";
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) playerName += "L";
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) playerName += "M";
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) playerName += "N";
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) playerName += "O";
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) playerName += "P";
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) playerName += "Q";
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) playerName += "R";
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) playerName += "S";
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) playerName += "T";
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) playerName += "U";
        if (Gdx.input.isKeyJustPressed(Input.Keys.V)) playerName += "V";
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) playerName += "W";
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) playerName += "X";
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) playerName += "Y";
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) playerName += "Z";
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) playerName += " ";
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && playerName.length() > 0)
            playerName = playerName.substring(0, playerName.length() - 1);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setPlayerName(playerName);
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        // Não descarte batch e font aqui, pois já são gerenciados pelo GameCore
    }
}

