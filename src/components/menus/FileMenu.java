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

        createItem("hoge", this, KeyEvent.VK_1, e -> CanvasArea.getManager().changeStatus("standard"));
        createItem("foge", this, KeyEvent.VK_2, e -> CanvasArea.getManager().changeStatus("high_speed"));
        createItem("goge", this, KeyEvent.VK_3, e -> CanvasArea.getManager().changeStatus("circle_moving"));
        createItem("quit", this, KeyEvent.VK_Q, e -> System.exit(1));
    }
}
