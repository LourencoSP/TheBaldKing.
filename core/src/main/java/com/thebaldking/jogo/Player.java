package com.thebaldking.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    private Rectangle playerBounds;
    private final float speed = 200; // Velocidade do jogador (pixels por segundo)
    private final ArrayList<Bullet> bullets = new ArrayList<>();

    public Player() {
        // Inicializa o jogador no centro da tela
        playerBounds = new Rectangle(400, 300, 30, 30);
    }

    public void update(float delta) {
        // Movimentação WASD
        if (Gdx.input.isKeyPressed(Input.Keys.W)) playerBounds.y += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) playerBounds.y -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) playerBounds.x -= speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) playerBounds.x += speed * delta;

        // Atirar (setas)
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) bullets.add(new Bullet(playerBounds.x + 10, playerBounds.y + 30, 0, 300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) bullets.add(new Bullet(playerBounds.x + 10, playerBounds.y - 10, 0, -300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) bullets.add(new Bullet(playerBounds.x - 10, playerBounds.y + 10, -300, 0));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) bullets.add(new Bullet(playerBounds.x + 30, playerBounds.y + 10, 300, 0));
        if (Gdx.input.isKeyJustPressed (Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) bullets.add(new Bullet(playerBounds.x - 10, playerBounds.y + 30, -300, 300));
        if (Gdx.input.isKeyJustPressed (Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) bullets.add(new Bullet(playerBounds.x -10, playerBounds.y - 5, -300, -300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)&& Gdx.input.isKeyPressed(Input.Keys.UP)) bullets.add(new Bullet(playerBounds.x + 30, playerBounds.y + 30, + 300, + 300));
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) bullets.add(new Bullet(playerBounds.x + 30, playerBounds.y - 5, + 300, - 300));



        // Atualiza os tiros
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update(delta);
            if (bullet.isOutOfBounds()) {
                iterator.remove(); // Remove balas fora da tela
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        // Renderiza o jogador
        shapeRenderer.rect(playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height);

        // Renderiza os tiros
        for (Bullet bullet : bullets) {
            bullet.render(shapeRenderer);
        }
    }
}

