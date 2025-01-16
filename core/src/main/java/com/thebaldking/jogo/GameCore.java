package com.thebaldking.jogo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thebaldking.jogo.enemy.Enemy;

public class GameCore extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    public SpriteBatch batch; // Adicionado SpriteBatch
    private Player player;
    private EnemyFactory enemyFactory; // Adiciona o EnemyFactory
    private Array<Enemy> enemies; // Lista de inimigos
    private float spawnTimer; // Temporizador para spawn de inimigos

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch(); // Inicializa o SpriteBatch
        player = new Player();
        enemyFactory = new EnemyFactory(); // Inicializa o EnemyFactory
        enemies = new Array<>(); // Inicializa a lista de inimigos
        spawnTimer = 0; // Inicializa o temporizador
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0.94f, 0.9f, 0.55f, 1); // Areia
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o jogador
        player.update(Gdx.graphics.getDeltaTime());

        // Atualiza o temporizador e gera inimigos
        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer > 2) { // Gera um inimigo a cada 2 segundos
            enemies.add(enemyFactory.createEnemy(player.getX(), player.getY()));
            spawnTimer = 0; // Reseta o temporizador
        }

        // Renderiza com ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(shapeRenderer);
        shapeRenderer.end();

        // Renderiza os inimigos com SpriteBatch
        batch.begin();
        for (Enemy enemy : enemies) {
            enemy.update(Gdx.graphics.getDeltaTime(), player.getX(), player.getY());
            enemy.render(batch); // Renderiza os inimigos
        }
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose(); // Libera os recursos do SpriteBatch
    }

    public SpriteBatch getBatch() {
        return batch; // Permite acessar o batch de fora da classe
    }
}
