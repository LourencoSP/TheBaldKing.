package com.thebaldking.jogo;

public class EnemyFactory {
    public com.thebaldking.jogo.Enemy createEnemy(float playerX, float playerY) {
        // Cria inimigos aleatoriamente em torno do jogador
        float x = (float) (Math.random() * 800);
        float y = (float) (Math.random() * 600);
        return new com.thebaldking.jogo.Enemy(x, y);
    }
}
