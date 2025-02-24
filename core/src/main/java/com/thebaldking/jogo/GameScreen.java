package com.thebaldking.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import enemy.Enemy;

import java.util.Iterator;

public class GameScreen implements Screen {
    private GameCore game;
    private Player player;
    private EnemyFactory enemyFactory;
    private Array<Enemy> enemies;
    private float spawnTimer;
    private int score;
    private boolean gameOver;

    public GameScreen(GameCore game) {
        this.game = game;
        player = new Player();
        enemyFactory = new EnemyFactory();
        enemies = new Array<>();
        spawnTimer = 0;
        score = 0;
        gameOver = false;
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        if (gameOver) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.spriteBatch.begin();
            game.font.draw(game.spriteBatch, "GAME OVER! Score: " + score, 300, 350);
            game.font.draw(game.spriteBatch, "Pressione R para reiniciar", 300, 300);
            game.spriteBatch.end();

            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                game.setScreen(new GameScreen(game));
            }
            return;
        }

        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o jogador
        player.update(delta);

        // Gera inimigos periodicamente
        spawnTimer += delta;
        if (spawnTimer > 1) {
            enemies.add(enemyFactory.createEnemy(player.getBounds().x, player.getBounds().y));
            spawnTimer = 0;
        }

        // Verifica colisões entre balas e inimigos
        checkBulletEnemyCollisions();

        // Verifica colisão entre o jogador e inimigos (game over)
        checkPlayerEnemyCollision();

        // Renderiza jogador e inimigos
        game.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
        player.render(game.shapeRenderer);
        for (Enemy enemy : enemies) {
            enemy.update(delta, player.getBounds().x, player.getBounds().y);
            enemy.render(game.shapeRenderer);
        }
        game.shapeRenderer.end();

        // Exibe pontuação
        game.spriteBatch.begin();
        game.font.draw(game.spriteBatch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);
        game.spriteBatch.end();
    }

    private void checkBulletEnemyCollisions() {
        Iterator<Bullet> bulletIterator = player.getBulletManager().getBullets().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (bullet.getBounds().overlaps(enemy.getBounds())) {
                    bulletIterator.remove();
                    enemyIterator.remove();
                    score++;
                    break;
                }
            }
        }
    }

    private void checkPlayerEnemyCollision() {
        for (Enemy enemy : enemies) {
            if (player.getBounds().overlaps(enemy.getBounds())) {
                gameOver = true;
                // Salva a pontuação no banco de dados (em outra thread)
                new Thread(() -> {
                    try {
                        DatabaseManager.saveScore(game.getPlayerName(), score);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            }
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
    public void dispose() { }
}
