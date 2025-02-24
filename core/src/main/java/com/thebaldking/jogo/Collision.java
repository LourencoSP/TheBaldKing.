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
        x += dx;
        y += dy;
    }

    public boolean collideWith(Collision other) {
        return x < other.x + other.width && x + width > other.x &&
            y < other.y + other.height && y + height > other.y;
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
