package enemy;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private Rectangle enemyBounds;
    private float speed = 125;

    public Enemy(float x, float y) {
        enemyBounds = new Rectangle(x, y, 30, 30); // Tamanho do inimigo
    }

    public void update(float delta, float playerX, float playerY) {
        float dx = playerX - enemyBounds.x;
        float dy = playerY - enemyBounds.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            dx /= distance;
            dy /= distance;
        }
        enemyBounds.x += dx * speed * delta;
        enemyBounds.y += dy * speed * delta;

        // Se estiver muito próximo, "prende" ao jogador
        if (distance < 5) {
            enemyBounds.x = playerX;
            enemyBounds.y = playerY;
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(enemyBounds.x, enemyBounds.y, enemyBounds.width, enemyBounds.height);
    }

    public Rectangle getBounds() {
        return enemyBounds;
    }
}
