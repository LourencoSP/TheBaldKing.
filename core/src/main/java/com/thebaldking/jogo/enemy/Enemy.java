package com.thebaldking.jogo.enemy;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

public abstract class Enemy {
    protected float x,y;
    protected float speed;
    protected ShapeRenderer shapeRenderer;

    public Enemy(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        shapeRenderer = new ShapeRenderer();

    }

    public abstract void update(float delta, float playerX, float playerY);

    public void render(SpriteBatch batch){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, 30, 30);
        shapeRenderer.end();
    }

    public void dispose(){
        shapeRenderer.dispose();
    }


}
