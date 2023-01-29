package me.xra1ny.pong;

import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Pong extends Game {
    private PongScreen pongScreen;

    public static void main(String[] args) {
        new Pong();
    }

    @Override
    public void onPropertiesCreation() {

    }

    @Override
    public void onEnable() {
        setTitle("Pong by xRa1ny");
        pongScreen = new PongScreen(this);

        show(pongScreen);
    }

    @Override
    public void onKeyPress(@NotNull KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void onKeyRelease(@NotNull KeyEvent keyEvent) {

    }

    @Override
    public void onMousePress(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseRelease(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseEnterComponent(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseExitComponent(@NotNull MouseEvent mouseEvent) {

    }
}
