package com.thebaldking.jogo;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private final GameCore game; // Referência ao GameCore
    private SpriteBatch batch;   // SpriteBatch para renderizar elementos
    private Texture playerTexture; // Textura para representar o jogador
    private float playerX, playerY; // Posição do jogador na tela

    public GameScreen(GameCore game) {
        this.game = game;
        this.batch = game.getBatch(); // Obtém o SpriteBatch do GameCore
    }

    @Override
    public void show() {
        // Inicialize os elementos do jogo aqui (jogador, inimigos, mapa, etc.)
        playerTexture = new Texture("player.png"); // Certifique-se de que o arquivo "player.png" está no diretório assets
        playerX = 100; // Posição inicial do jogador no eixo X
        playerY = 100; // Posição inicial do jogador no eixo Y
    }

    @Override
    public void render(float delta) {
        // Limpa a tela com uma cor de fundo
        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1); // Areia
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualize a lógica do jogo aqui, se necessário
        playerX += 50 * delta; // Exemplo: Move o jogador no eixo X

        // Renderize os elementos do jogo
        batch.begin();
        batch.draw(playerTexture, playerX, playerY); // Renderiza o jogador na tela
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Código para lidar com redimensionamento da tela, se necessário
    }

    @Override
    public void pause() {
        // Código para pausar o jogo, se necessário
    }

    @Override
    public void resume() {
        // Código para retomar o jogo, se necessário
    }

    @Override
    public void hide() {
        // Código para ocultar elementos da tela, se necessário
    }

    @Override
    public void dispose() {
        // Libere os recursos usados para evitar vazamentos de memória
        playerTexture.dispose();
    }
}
