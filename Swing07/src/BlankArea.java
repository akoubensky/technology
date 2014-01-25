/*
 * BlankArea.java is used by:
 *    MouseEventDemo.java.
 *    MouseMotionEventDemo.java
 */

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Color;

@SuppressWarnings("serial")
public class BlankArea extends JLabel {
    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
