package components.menus;

import components.CanvasArea;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ktr on 2017/03/29.
 */
public class FileMenu extends MenuAbstract {

    public FileMenu(String title, HashMap<String, JMenuItem> menuItems) {
        super(title, menuItems);

        createItem("hoge", this, KeyEvent.VK_F1, e -> CanvasArea.getM().change_status("standard"));
        createItem("foge", this, KeyEvent.VK_F2, e -> CanvasArea.getM().change_status("high_speed"));
        createItem("goge", this, KeyEvent.VK_F3, e -> CanvasArea.getM().change_status("circle_moving"));
        createItem("quit", this, KeyEvent.VK_Q, e -> System.exit(1));
    }
}
