package components;

import components.menus.EditMenu;
import components.menus.FileMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/19.
 */
class CustomMenubar extends JMenuBar {
    private HashMap<String, JMenuItem> menuItems;

    CustomMenubar() {
        menuItems = new HashMap<>();

        FileMenu fileMenu = new FileMenu("File", menuItems);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        EditMenu editMenu = new EditMenu("Edit", menuItems);
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);
    }
}
