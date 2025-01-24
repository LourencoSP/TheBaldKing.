package com.thebaldking.jogo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import enemy.Enemy;

public class GameCore extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Player player;
    private EnemyFactory enemyFactory;
    private Array<Enemy> enemies;
    private float spawnTimer;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        player = new Player();
        enemyFactory = new EnemyFactory();
        enemies = new Array<>();
        spawnTimer = 0;
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1); // Cor de fundo
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o jogador
        player.update(Gdx.graphics.getDeltaTime());

        // Gerencia a criação de inimigos
        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer > 1) {
            enemies.add(enemyFactory.createEnemy(player.getBounds().x, player.getBounds().y));
            spawnTimer = 0;
        }

        // Verifica colisão entre balas e inimigos
        checkCollisions();

        // Renderiza a tela
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(shapeRenderer); // Renderiza o jogador
        for (Enemy enemy : enemies) {
            enemy.update(Gdx.graphics.getDeltaTime(), player.getBounds().x, player.getBounds().y); // Atualiza os inimigos
            enemy.render(shapeRenderer); // Renderiza os inimigos
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void checkCollisions() {

        Iterator<Bullet> bulletIterator = player.getBulletManager().getBullets().iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();

            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                // Verifica colisão entre a bala e o inimigo
                if (bullet.getBounds().overlaps(enemy.getBounds())) {
                    bulletIterator.remove(); // Remove a bala
                    enemyIterator.remove();// Remove o inimigo

                    break; // Colisão detectada, não precisa verificar mais inimigos para essa bala
                }
            }
        }
    }
}
