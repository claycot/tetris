package com.claycot.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

public class KeyboardInput implements KeyListener {
    // ms delay for repeated key actions (ie. holding down key)
    public static final int REPEAT_DELAY = 150;

    // create a map that will allow any key to repeat on hold
    private Map<Integer, Timer> heldKeyTimers = new HashMap<>();

    // remappable keys
    private Map<Integer, Runnable> keyActions = new HashMap<>();

    public KeyboardInput() {
        keyActions.put(KeyEvent.VK_LEFT, () -> System.out.println("move left"));
        keyActions.put(KeyEvent.VK_RIGHT, () -> System.out.println("move right"));
        keyActions.put(KeyEvent.VK_UP, () -> System.out.println("drop piece instantly"));
        keyActions.put(KeyEvent.VK_DOWN, () -> System.out.println("hasten downwards"));
        keyActions.put(KeyEvent.VK_SPACE, () -> System.out.println("rotate clockwise"));
        keyActions.put(KeyEvent.VK_Z, () -> System.out.println("rotate counterclockwise"));
        keyActions.put(KeyEvent.VK_X, () -> System.out.println("rotate clockwise"));
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
