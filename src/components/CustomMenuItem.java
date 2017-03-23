package components;

import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

/**
 * Created by ktr on 2017/03/19.
 */
class CustomMenuItem extends JMenuItem {
    CustomMenuItem(String name) {
        super(name);

        char key = name.charAt(0);
        setMnemonic(key);
    }
}
