package components;

import pub.controll.Manager;
import pub.controll.Manager.*;
import pub.controll.act.HighSpeed;
import pub.controll.act.Standard;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/19.
 */
class CustomMenubar extends JMenuBar {
    private HashMap<String, JMenuItem> menuItems;

    CustomMenubar() {
        menuItems = new HashMap<>();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        createItem("test", fileMenu);
        setShortCutKey(this.menuItems.get("test"), KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
        this.menuItems.get("test").addActionListener(e -> {
            if (CanvasArea.getM().getS() instanceof Standard) CanvasArea.getM().change_status("high_speed");
        });

        createItem("quit", fileMenu);
        setShortCutKey(this.menuItems.get("quit"), KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        this.menuItems.get("quit").addActionListener(e -> System.exit(1));
    }

    private void createItem(String name, JMenu target) {
        JMenuItem item = new CustomMenuItem(name);
        this.menuItems.put(name, item);
        target.add(item);
    }

    private void setShortCutKey(JMenuItem target, int key, int maskKey) {
        target.setAccelerator(KeyStroke.getKeyStroke(key, maskKey));
    }
}
