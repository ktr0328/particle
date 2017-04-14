package components.menus;

import components.ConfigFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/29.
 */
public class EditMenu extends MenuAbstract {
    public EditMenu(String title, HashMap<String, JMenuItem> menuItems) {
        super(title, menuItems);
        ConfigFrame cf = new ConfigFrame("Config");

        createItem("config", this, KeyEvent.VK_P, e -> cf.setVisible(true));
    }
}
