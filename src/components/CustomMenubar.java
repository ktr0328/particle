package components;

import pub.controll.Manager;
import pub.controll.Manager.*;
import pub.controll.act.HighSpeed;
import pub.controll.act.Standard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.function.Function;

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

        createItem("foo", fileMenu, KeyEvent.VK_F, e -> CanvasArea.getM().change_status("high_speed"));
        createItem("hoge", fileMenu, KeyEvent.VK_H, e -> CanvasArea.getM().change_status("standard"));
        createItem("quit", fileMenu, KeyEvent.VK_Q, e -> System.exit(1));
    }

    private void createItem(String name, JMenu target, int keyCode, ActionListener l) {
        JMenuItem item = new CustomMenuItem(name);
        this.menuItems.put(name, item);
        setShortCutKey(this.menuItems.get(name), keyCode, KeyEvent.CTRL_DOWN_MASK);
        item.addActionListener(l);

        target.add(item);
    }

    private void setShortCutKey(JMenuItem target, int key, int maskKey) {
        target.setAccelerator(KeyStroke.getKeyStroke(key, maskKey));
    }

}
