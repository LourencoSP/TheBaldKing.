package com.thebaldking.jogo.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.thebaldking.jogo.GameCore;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        new Lwjgl3Application(new GameCore(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("TheBaldKing");
        config.useVsync(true);
        config.setWindowedMode(800, 600);
        config.setResizable(false);
        return config;
    }
}
