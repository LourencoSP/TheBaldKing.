package com.thebaldking.jogo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Rectangle bulletBounds;
    private float speedX, speedY;

    public Bullet(float x, float y, float speedX, float speedY) {
        bulletBounds = new Rectangle(x, y, 5, 5); // Tamanho do tiro (5x5)
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void update(float delta) {
        bulletBounds.x += speedX * delta;
        bulletBounds.y += speedY * delta;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bulletBounds.x, bulletBounds.y, bulletBounds.width, bulletBounds.height);
    }

    public Rectangle getBounds() {
        return bulletBounds;
    }

    public boolean isOutOfBounds() {
        // Considerando uma tela de 800x600
        return bulletBounds.x < 0 || bulletBounds.x > 800 || bulletBounds.y < 0 || bulletBounds.y > 600;
    }
}
