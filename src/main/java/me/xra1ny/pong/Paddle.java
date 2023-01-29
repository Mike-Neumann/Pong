package me.xra1ny.pong;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.gameapi.CollisionSide;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.objects.Entity;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Paddle extends Entity {
    @Getter
    @Setter
    private boolean isLeftDown;

    @Getter
    @Setter
    private boolean isRightDown;

    @Override
    public TickResult onTick(@NotNull GameScreen gameScreen) {
        final PongScreen pongScreen = (PongScreen) gameScreen;
        final Ball ball = pongScreen.getBall();

        if(collidesWith(ball, pongScreen.getGame().getCollisionTolerance())) {
            final CollisionSide collisionSide = getCollisionSide(ball, pongScreen.getGame().getCollisionTolerance());
            pongScreen.getGame().getSoundEngine().playSound("paddle.wav");

            if(collisionSide == CollisionSide.TOP) {
                ball.setYVelocity(-2);
            }else if(collisionSide == CollisionSide.BOTTOM) {
                ball.setYVelocity(2);
            }else if(collisionSide == CollisionSide.LEFT) {
                ball.setXVelocity(-2+(getXVelocity()*2));
                ball.setYVelocity(-2);
            }else if(collisionSide == CollisionSide.RIGHT) {
                ball.setXVelocity(2+(getXVelocity()*2));
                ball.setYVelocity(-2);
            }

            return TickResult.DEFAULT;
        }

        if(getX() < 0) {
            setXVelocity(0);
            setX(0);
        }else if(getX()+getWidth() > gameScreen.getGame().getWidth()) {
            setXVelocity(0);
            setX(gameScreen.getGame().getWidth()-getWidth());
        }else {
            if(isLeftDown) {
                setXVelocity(-3);
            }else if(isRightDown) {
                setXVelocity(3);
            }else {
                setXVelocity(0);
            }
        }

        return TickResult.DEFAULT;
    }

    @Override
    public double getWidth() {
        return 75;
    }

    @Override
    public double getHeight() {
        return 20;
    }

    @Override
    public RenderResult onRender(@NotNull GameScreen screen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.WHITE);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());

        return RenderResult.DEFAULT;
    }
}
