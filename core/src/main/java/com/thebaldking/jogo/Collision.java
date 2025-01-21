package com.thebaldking.jogo;

public class Collision {
    private float x, y;
    private int width, height;

    public Collision(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean collideWith(Collision other) {
        return x < other.x + other.width && y < other.y + other.height &&
            x + width > other.x && y + height > other.y;
    }

    // Getters e Setters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
