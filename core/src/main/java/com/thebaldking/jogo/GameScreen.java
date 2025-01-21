package com.thebaldking.jogo;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private GameCore gameCore;

    public GameScreen(GameCore gameCore) {
        this.gameCore = gameCore;
    }

    @Override
    public void show() {
        // Inicialização da tela, se necessário
    }

    @Override
    public void render(float delta) {
        gameCore.render();
    }

    @Override
    public void resize(int width, int height) {
        // Responsável por redimensionar a tela
    }

    @Override
    public void hide() {
        // Quando a tela for escondida
    }

    @Override
    public void pause() {
        // Quando o jogo for pausado
    }

    @Override
    public void resume() {
        // Quando o jogo for retomado
    }

    @Override
    public void dispose() {
        // Limpeza de recursos
    }
}
