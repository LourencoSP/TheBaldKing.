package com.thebaldking.jogo;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Iterator;

public class BulletManager {
    private ArrayList<Bullet> bullets;

    public BulletManager() {
        bullets = new ArrayList<>();
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void update(float delta) {
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
        for (Bullet bullet : bullets) {
            bullet.render(shapeRenderer);
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
