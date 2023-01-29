package me.xra1ny.pong;

import lombok.Getter;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PongScreen extends GameScreen {
    @Getter(onMethod = @__(@NotNull))
    private Ball ball;

    @Getter(onMethod = @__(@NotNull))
    private Paddle paddle;

    public PongScreen(@NotNull Game game) {
        super(game);
    }

    @Override
    public void renderTick(@NotNull Graphics2D gtd) {

    }

    @Override
    public void onEnable() {
        ball = new Ball();
        paddle = new Paddle();

        paddle.setX((double) (getGame().getWidth()/2) - paddle.getWidth()/2);
        paddle.setY(getGame().getHeight()-(paddle.getHeight()*5));

        ball.setX(paddle.getX() + (paddle.getWidth()/2) - (ball.getWidth()/2));
        ball.setY(paddle.getY() - (ball.getHeight()*3));
        ball.setYVelocity(3);

        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 5; y++) {
                final Block block = new Block();
                block.setX((double) (getGame().getWidth()/2) - (double) (block.getWidth()/2));
                block.setX(block.getX() + (double) (5*block.getWidth()) - (double) (x*block.getWidth()) - block.getWidth());
                block.setY(y*block.getHeight() + (double) ((getGame().getHeight()/2)/2));

                getGameObjects().add(block);
            }
        }

        getGameObjects().add(ball);
        getGameObjects().add(paddle);

        setBackground(Color.BLACK);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.setRightDown(true);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setLeftDown(true);
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.setRightDown(false);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setLeftDown(false);
        }
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
