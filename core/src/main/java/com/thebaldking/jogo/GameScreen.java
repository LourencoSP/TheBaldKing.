package com.thebaldking.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import enemy.Enemy;

import java.util.Iterator;
import java.util.Locale;

public class GameScreen implements Screen {
    private GameCore game;
    private Player player;
    private EnemyFactory enemyFactory;
    private Array<Enemy> enemies;
    private int score;
    private boolean gameOver;

    //Variáveis de controle de tempo e dificuldade
    private float spawnTimer;
    private float spawnInterval = 2f;
    private final float minSpawnInterval = 0.01f;
    private final float difficultyIncreaseRate = 0.02f;

    //Variável para o cronômetro de sobrevivência
    private float survivalTime;

    public GameScreen(GameCore game) {
        this.game = game;
        player = new Player();
        enemyFactory = new EnemyFactory();
        enemies = new Array<>();
        spawnTimer = 0;
        score = 0;
        gameOver = false;
        survivalTime = 0f; //Inicializa o tempo de sobrevivência
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Lógica da tela de Game Over
        if (gameOver) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.spriteBatch.begin();
            //Formata o tempo para exibir minutos e segundos
            String formattedTime = String.format(Locale.US, "%02d:%02d", (int) (survivalTime / 60), (int) (survivalTime % 60));
            game.font.draw(game.spriteBatch, "GAME OVER!", 350, 400);
            game.font.draw(game.spriteBatch, "Score: " + score, 350, 350);
            game.font.draw(game.spriteBatch, "Tempo: " + formattedTime, 350, 325); // Exibe o tempo final
            game.font.draw(game.spriteBatch, "Pressione R para reiniciar", 320, 280);
            game.spriteBatch.end();

            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                game.setScreen(new GameScreen(game));
            }
            return;
        }

        //Atualiza o tempo de sobrevivência a cada quadro
        survivalTime += delta;

        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update(delta);

        spawnTimer += delta;
        if (spawnInterval > minSpawnInterval) {
            spawnInterval -= delta * difficultyIncreaseRate;
        }
        if (spawnTimer >= spawnInterval) {
            enemies.add(enemyFactory.createEnemy(player.getBounds().x, player.getBounds().y));
            spawnTimer = 0;
        }

        checkBulletEnemyCollisions();
        checkPlayerEnemyCollision();

        game.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
        player.render(game.shapeRenderer);
        for (Enemy enemy : enemies) {
            enemy.update(delta, player.getBounds().x, player.getBounds().y);
            enemy.render(game.shapeRenderer);
        }
        game.shapeRenderer.end();

        //Exibe pontuação e o cronômetro na tela
        game.spriteBatch.begin();
        game.font.draw(game.spriteBatch, "Score: " + score, 10, Gdx.graphics.getHeight() - 10);

        //Formata e exibe o tempo de sobrevivência no canto superior direito
        String formattedTime = String.format(Locale.US, "%02d:%02d", (int) (survivalTime / 60), (int) (survivalTime % 60));
        game.font.draw(game.spriteBatch, "Tempo: " + formattedTime, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 10);

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
