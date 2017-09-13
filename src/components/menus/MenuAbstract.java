package components.menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/29.
 */
abstract class MenuAbstract extends JMenu {
    private HashMap<String, JMenuItem> menuItems;

    MenuAbstract(String title, HashMap<String, JMenuItem> menuItems) {
        super(title);
        this.menuItems = menuItems;
    }

    void createItem(String name, JMenu target, int keyCode, ActionListener l) {
        JMenuItem item = new CustomMenuItem(name);
        this.menuItems.put(name, item);
        setShortCutKey(this.menuItems.get(name), keyCode, InputEvent.ALT_DOWN_MASK);
        item.addActionListener(l);

        target.add(item);
    }

    private void setShortCutKey(JMenuItem target, int key, int maskKey) {
        target.setAccelerator(KeyStroke.getKeyStroke(key, maskKey));
    }
}
