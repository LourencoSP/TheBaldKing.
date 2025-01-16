package com.thebaldking.jogo.enemy;

public class FastEnemy extends Enemy {

    public FastEnemy(float x, float y, float speed) {
        super(x, y, speed);
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

