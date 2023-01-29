package me.xra1ny.pong;

import me.xra1ny.gameapi.CollisionSide;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Block extends Entity {
    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        final PongScreen pongScreen = (PongScreen) gameScreen;
        final Ball ball = pongScreen.getBall();

        if(collidesWith(ball, pongScreen.getGame().getCollisionTolerance())) {
            pongScreen.getGame().getSoundEngine().playSound("block.wav");
            final CollisionSide collisionSide = getCollisionSide(ball, pongScreen.getGame().getCollisionTolerance());

            if(collisionSide == CollisionSide.TOP) {
                ball.setYVelocity(-3);
            }else if(collisionSide == CollisionSide.BOTTOM) {
                ball.setYVelocity(3);
            }else if(collisionSide == CollisionSide.LEFT) {
                ball.setXVelocity(-3);
            }else if(collisionSide == CollisionSide.RIGHT) {
                ball.setXVelocity(3);
            }else {
                ball.setXVelocity(-ball.getXVelocity());
                ball.setYVelocity(-ball.getYVelocity());
            }

            pongScreen.getGameObjects().remove(this);

            return TickResult.ESCAPE;
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 25;
    }

    @Override
    public double getHeight() {
        return 25;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.WHITE);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        return RenderResult.DEFAULT;
    }
}
