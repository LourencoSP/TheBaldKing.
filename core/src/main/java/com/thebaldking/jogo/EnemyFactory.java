package com.thebaldking.jogo;

import enemy.Enemy;

public class EnemyFactory {
    public Enemy createEnemy(float playerX, float playerY) {
        float x = 0, y = 0;


        int border = (int) (Math.random() * 4);

        switch (border) {
            case 0: // Topo
                x = (float) (Math.random() * 800);
                y = 600; // Borda superior
                break;
            case 1: // Baixo
                x = (float) (Math.random() * 800);
                y = 0; // Borda inferior
                break;
            case 2: // Esquerda
                x = 0; // Borda esquerda
                y = (float) (Math.random() * 600);
                break;
            case 3: // Direita
                x = 800; // Borda direita
                y = (float) (Math.random() * 600);
                break;
            default:
                break;
        }


        return new Enemy(x, y);
    }
}
