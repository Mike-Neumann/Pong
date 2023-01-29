package me.xra1ny.pong;

import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Ball extends Entity {
    public Ball() {

    }

    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        final PongScreen pongScreen = (PongScreen) gameScreen;
        final Paddle paddle = pongScreen.getPaddle();

        if(getXVelocity() > 2 || getXVelocity() < -2) {
            setXVelocity(getXVelocity()*.9);
        }

        if(getYVelocity() > 2 || getYVelocity() < -2) {
            setYVelocity(getYVelocity()*.9);
        }

        if(getX() < 0) {
            setXVelocity(3);
            pongScreen.getGame().getSoundEngine().playSound("block.wav");
        }else if(getX()+getWidth() > gameScreen.getGame().getWidth()-getWidth()) {
            setXVelocity(-3);
            pongScreen.getGame().getSoundEngine().playSound("block.wav");
        }

        if(getY() < 0) {
            setYVelocity(3);
            pongScreen.getGame().getSoundEngine().playSound("block.wav");
        }

        if(getY()+getHeight() > paddle.getY()+(paddle.getHeight()*2)) {
            pongScreen.getGame().getSoundEngine().playSound("death.wav");
            setAllowTick(false);
            setAllowRender(false);
            paddle.setAllowTick(false);


            pongScreen.getGameObjects().remove(this);
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 15;
    }

    @Override
    public double getHeight() {
        return 15;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.WHITE);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        return RenderResult.DEFAULT;
    }
}
