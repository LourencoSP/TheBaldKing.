package enemy;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private Rectangle enemyBounds;
    private float speed = 125; // Velocidade do inimigo

    public Enemy(float x, float y) {
        enemyBounds = new Rectangle(x, y, 30, 30); // Tamanho do inimigo
    }

    public void update(float delta, float playerX, float playerY) {
        // Calcula a diferença nas posições
        float dx = playerX - enemyBounds.x;
        float dy = playerY - enemyBounds.y;

        // Calcula a magnitude da distância (hipotenusa)
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Normaliza a direção para que o movimento não seja afetado pela distância
        if (distance > 0) {
            dx /= distance;
            dy /= distance;
        }


        enemyBounds.x += dx * speed * delta;
        enemyBounds.y += dy * speed * delta;

        // Verifica se o inimigo alcançou o jogador (distância mínima)
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
