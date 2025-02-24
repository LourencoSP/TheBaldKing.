package com.thebaldking.jogo;

import enemy.Enemy;
import com.badlogic.gdx.math.MathUtils;

public class EnemyFactory {
    public Enemy createEnemy(float playerX, float playerY) {
        float x = 0, y = 0;
        int border = MathUtils.random(0, 3);

        switch (border) {
            case 0: // Topo
                x = MathUtils.random(0, 800);
                y = 600;
                break;
            case 1: // Baixo
                x = MathUtils.random(0, 800);
                y = 0;
                break;
            case 2: // Esquerda
                x = 0;
                y = MathUtils.random(0, 600);
                break;
            case 3: // Direita
                x = 800;
                y = MathUtils.random(0, 600);
                break;
        }
        return new Enemy(x, y);
    }
}
