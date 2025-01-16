package com.thebaldking.jogo;

import com.thebaldking.jogo.enemy.Enemy;
import com.thebaldking.jogo.enemy.FastEnemy;
import com.thebaldking.jogo.enemy.TankyEnemy;

import java.util.Random;

public class EnemyFactory {
    private Random random;

    public EnemyFactory(){
        this.random = new Random();
    }

    public Enemy createEnemy(float playerX, float playerY){
        float x,y;
        int side = random.nextInt(4);
        switch (side){
            case 0://vai nascer em cima
                x = random.nextInt(799);
                y = 600;
                break;
            case 1://vai nascer embaixo
                x = random.nextInt(799);
                y = -50;
                break;
            case 2:// na esquerda
                x = -50;
                y = random.nextInt(599);
                break;
            case 3:// na direita
                x = 800;
                y = random.nextInt(799);
                break;
            default:
                x = 0;
                y = 0;
        }
        int enemyType = random.nextInt(2);
        switch (enemyType){
            case 0:
                return new FastEnemy(x, y, 100);
            case 1:
                return new TankyEnemy(x, y, 90);
            default:
                return null;
        }
    }
}
