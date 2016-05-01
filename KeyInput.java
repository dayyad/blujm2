import ecs100.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KeyInput implements KeyListener
{
    // instance variables - replace the example below with your own
    public ArrayList<Integer> input_keys = new ArrayList<Integer>();
    
    public KeyInput()
    {
        
    }
    
    // Function that only adds a key to the ArrayList if it does not aleady contain it.
    private boolean add_key(int key) {
        for (int i = 0; i < input_keys.size(); i++) {
            if (input_keys.get(i).intValue() == key) {
                return false;
            }
        }
        
        input_keys.add((Integer)key);
        return true;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        add_key(key);

        // Debugging prints
        for (int i = 0; i < input_keys.size(); i++) {
            UI.print(input_keys.get(i));
        }
        UI.println("");
    }
    
    public void keyReleased(KeyEvent e) {
        input_keys.remove((Integer)e.getKeyCode());
    }
    
    public void keyTyped(KeyEvent e) {}
}