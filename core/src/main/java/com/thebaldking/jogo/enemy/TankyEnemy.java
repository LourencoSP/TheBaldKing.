package com.thebaldking.jogo.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TankyEnemy extends Enemy {

    public TankyEnemy(float x, float y, float speed) {
        super(x, y, speed);
    }

    @Override
    public void render(SpriteBatch batch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, 30, 30);
        shapeRenderer.end();
    }

    @Override
    public void update(float delta, float playerX, float playerY) {
        // Calcula a diferença nas posições
        float dx = playerX - x;
        float dy = playerY - y;

        // Calcula a magnitude da distância (hipotenusa)
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Normaliza a direção para que o movimento não seja afetado pela distância
        if (distance > 0) {
            dx /= distance;
            dy /= distance;
        }

        // Move o inimigo em direção ao jogador
        x += dx * speed * delta;
        y += dy * speed * delta;

        // Verifica se o inimigo alcançou o jogador (distância mínima)
        if (distance < 5) { // Distância mínima para parar, ajuste conforme necessário
            x = playerX;
            y = playerY;
        }
    }
}
