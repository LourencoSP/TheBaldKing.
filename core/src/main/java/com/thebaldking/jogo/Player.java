package com.thebaldking.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Rectangle playerBounds;
    private final float speed = 200; // Velocidade do jogador (pixels por segundo)
    private BulletManager bulletManager;

    public Player() {
        playerBounds = new Rectangle(400, 300, 30, 30); // Posicionado no centro da tela
        bulletManager = new BulletManager();
    }

    public void update(float delta) {
        // Movimentação com WASD
        if (Gdx.input.isKeyPressed(Input.Keys.W)) playerBounds.y += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) playerBounds.y -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) playerBounds.x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) playerBounds.x += speed * delta;

        // Disparo com setas
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            bulletManager.addBullet(new Bullet(playerBounds.x + 10, playerBounds.y + 30, 0, 300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            bulletManager.addBullet(new Bullet(playerBounds.x + 10, playerBounds.y - 10, 0, -300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            bulletManager.addBullet(new Bullet(playerBounds.x - 10, playerBounds.y + 10, -300, 0));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            bulletManager.addBullet(new Bullet(playerBounds.x + 30, playerBounds.y + 10, 300, 0));

        bulletManager.update(delta);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);
        bulletManager.render(shapeRenderer);
    }

    public Rectangle getBounds() {
        return playerBounds;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }
}
