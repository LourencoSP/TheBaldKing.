
/*package com.thebaldking.jogo;

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

        playerTexture = new Texture("player.png");
        playerX = 100; // Posição inicial do jogador no eixo X
        playerY = 100; // Posição inicial do jogador no eixo Y
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1); // Areia
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        playerX += 50 * delta;


        batch.begin();
        batch.draw(playerTexture, playerX, playerY); // Renderiza o jogador na tela
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        playerTexture.dispose();
    }
}*/
package com.thebaldking.jogo;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thebaldking.jogo.enemy.Enemy;

import java.util.Iterator;

public class GameScreen implements Screen {

    private final GameCore game;
    private SpriteBatch batch;
    private Texture playerTexture;
    private float playerX, playerY;
    private Player player;
    private EnemyFactory enemyFactory;
    private Array<Enemy> enemies;
    private float spawnTimer;

    public GameScreen(GameCore game) {
        this.game = game;
        this.batch = game.getBatch();
    }

    @Override
    public void show() {
        player = new Player();
        playerTexture = new Texture("player.png");
        playerX = 100;
        playerY = 100;

        enemyFactory = new EnemyFactory();
        enemies = new Array<>();
        spawnTimer = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1); // Areia
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o temporizador para gerar inimigos
        spawnTimer += delta;
        if (spawnTimer > 1) {
            enemies.add(enemyFactory.createEnemy(player.getX(), player.getY()));
            spawnTimer = 0;
        }

        // Atualiza o jogador (simples movimentação)
        playerX += 50 * delta;

        // Renderiza todos os elementos na tela
        batch.begin();

        // Renderiza os inimigos
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
            Enemy enemy = iterator.next();
            enemy.update(delta, player.getX(), player.getY());
            enemy.render(batch);
        }

        // Renderiza o jogador
        batch.draw(playerTexture, playerX, playerY);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        playerTexture.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}

