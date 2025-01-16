package com.thebaldking.jogo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Rectangle bounds;
    private float speedX, speedY;

    public Bullet(float x, float y, float speedX, float speedY) {
        bounds = new Rectangle(x, y, 5, 5); // Quadrado pequeno
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void update(float delta) {
        bounds.x += speedX * delta;
        bounds.y += speedY * delta;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public boolean isOutOfBounds() {

        return bounds.x < 0 || bounds.x > 800 || bounds.y < 0 || bounds.y > 600;
    }
}

