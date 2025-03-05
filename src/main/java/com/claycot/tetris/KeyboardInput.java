package com.claycot.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class KeyboardInput implements KeyListener {
    // ms delay for repeated key actions (ie. quickly pressing the same key)
    private static final int DEBOUNCE_DELAY = 50;
    // ms delay for repeated held key actions (ie. holding the key to repeat action)
    private static final int REPEAT_DELAY = 150;
    private Game game;

    // create a map that will allow any key to repeat on hold
    private Map<Integer, Timer> debounceTimers = new HashMap<>();
    private Map<Integer, Timer> heldKeyTimers = new HashMap<>();

    // remappable keys
    private Map<Integer, Runnable> keyActions = new HashMap<>();

    public KeyboardInput(Game game) {
        this.game = game;
        keyActions.put(KeyEvent.VK_LEFT, () -> this.game.moveTetrominoCenter(Movement.LEFT));
        keyActions.put(KeyEvent.VK_RIGHT, () -> this.game.moveTetrominoCenter(Movement.RIGHT));
        keyActions.put(KeyEvent.VK_UP, () -> this.game.moveTetrominoCenter(Movement.SLAM));
        keyActions.put(KeyEvent.VK_DOWN, () -> this.game.moveTetrominoCenter(Movement.DROP));
        keyActions.put(KeyEvent.VK_SPACE, () -> this.game.getTetromino().rotate(true, this.game.getTetrominoCenter(), this.game.getOccupied()));
        keyActions.put(KeyEvent.VK_Z, () -> this.game.getTetromino().rotate(false, this.game.getTetrominoCenter(), this.game.getOccupied()));
        keyActions.put(KeyEvent.VK_X, () -> this.game.getTetromino().rotate(true, this.game.getTetrominoCenter(), this.game.getOccupied()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // don't allow keypresses if key has a timer running
        if (debounceTimers.containsKey(key) || heldKeyTimers.containsKey(key)) {
            return;
        }

        // immediately perform the action
        performAction(key);

        // create a timer in case the key is held
        Timer timer = new Timer(REPEAT_DELAY, event -> performAction(key));
        timer.setRepeats(true);
        timer.start();
        heldKeyTimers.put(key, timer);

        // create a debounce timer
        Timer debounceTimer = new Timer(DEBOUNCE_DELAY, event -> removeDebounceTimer(key));
        debounceTimer.setRepeats(true);
        debounceTimer.start();
        debounceTimers.put(key, debounceTimer);
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

    private void removeDebounceTimer(int key) {        
        Timer timer = debounceTimers.remove(key);
        if (timer != null) {
            timer.stop();
        }
    }
}
