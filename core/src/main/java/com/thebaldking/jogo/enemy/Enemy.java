package com.thebaldking.jogo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private Rectangle enemyBounds;
    private float speed = 100; // Velocidade do inimigo

    public Enemy(float x, float y) {
        enemyBounds = new Rectangle(x, y, 30, 30); // Tamanho do inimigo
    }

    public void update(float delta, float playerX, float playerY) {
        // Movimento básico em direção ao jogador
        if (enemyBounds.x < playerX) enemyBounds.x += speed * delta;
        if (enemyBounds.x > playerX) enemyBounds.x -= speed * delta;
        if (enemyBounds.y < playerY) enemyBounds.y += speed * delta;
        if (enemyBounds.y > playerY) enemyBounds.y -= speed * delta;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(enemyBounds.x, enemyBounds.y, enemyBounds.width, enemyBounds.height);
    }

    public Rectangle getBounds() {
        return enemyBounds;
    }
}
