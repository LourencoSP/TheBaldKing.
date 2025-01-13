package com.thebaldking.jogo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameCore extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    public SpriteBatch batch; // Adicionado SpriteBatch
    private Player player;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch(); // Inicializa o SpriteBatch
        player = new Player();
    }

    @Override
    public void render() {
        // Limpa a tela
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Atualiza o jogador
        player.update(Gdx.graphics.getDeltaTime());

        // Renderiza com ShapeRenderer
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(shapeRenderer);
        shapeRenderer.end();

        // Renderiza com SpriteBatch
        batch.begin();
        // Adicione aqui qualquer coisa que precise do batch
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


