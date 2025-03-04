package com.claycot.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class KeyboardInput implements KeyListener {
    // ms delay for repeated key actions (ie. holding down key)
    private static final int REPEAT_DELAY = 150;
    private Game game;

    // create a map that will allow any key to repeat on hold
    private Map<Integer, Timer> heldKeyTimers = new HashMap<>();

    // remappable keys
    private Map<Integer, Runnable> keyActions = new HashMap<>();

    public KeyboardInput(Game game) {
        this.game = game;
        keyActions.put(KeyEvent.VK_LEFT, () -> this.game.moveTetrominoCenter(Movement.LEFT));
        keyActions.put(KeyEvent.VK_RIGHT, () -> this.game.moveTetrominoCenter(Movement.RIGHT));
        keyActions.put(KeyEvent.VK_UP, () -> this.game.moveTetrominoCenter(Movement.SLAM));
        keyActions.put(KeyEvent.VK_DOWN, () -> this.game.moveTetrominoCenter(Movement.DROP));
        keyActions.put(KeyEvent.VK_SPACE, () -> this.game.getTetromino().rotate(true, null));
        keyActions.put(KeyEvent.VK_Z, () -> this.game.getTetromino().rotate(false, null));
        keyActions.put(KeyEvent.VK_X, () -> this.game.getTetromino().rotate(true, null));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // don't allow keypresses if key has a timer running
        if (heldKeyTimers.containsKey(key)) {
            return;
        }

        // immediately perform the action
        performAction(key);

        // create a timer in case the key is held
        Timer timer = new Timer(REPEAT_DELAY, event -> performAction(key));
        timer.setRepeats(true);
        timer.start();
        heldKeyTimers.put(key, timer);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // remove timer from the map
        int key = e.getKeyCode();
        Timer timer = heldKeyTimers.remove(key);
        if (timer != null) {
            timer.stop();
        }
    }

    private void performAction(int key) {
        // perform the key action
        Runnable action = keyActions.get(key);
        if (action != null) {
            action.run();
        }
    }
}
