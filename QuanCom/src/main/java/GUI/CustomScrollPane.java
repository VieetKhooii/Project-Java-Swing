package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollPane extends JScrollPane {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final Color thumbColor = new Color(120, 144, 156);
    private final Color trackColor = new Color(228, 232, 235);

    public CustomScrollPane(Component view) {
        super(view);
        customizeScrollBarUI();
    }

    private void customizeScrollBarUI() {
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI());

        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new CustomScrollBarUI());
    }

    // Custom ScrollBarUI for ChatGPT-style scrollbar
    private class CustomScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(trackColor);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            g.setColor(thumbColor);
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom ScrollPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae turpis a augue commodo ultrices. Nullam vulputate rhoncus nisi sit amet rutrum. Etiam sit amet lorem at mi maximus fringilla nec at libero. Donec vitae massa nec mi congue tincidunt. Phasellus eleifend tortor ut diam porttitor, sed lobortis tortor posuere. Sed ultricies tellus lacus, vel tempus elit consectetur non. Suspendisse faucibus libero ac mauris consectetur, ac egestas nisi eleifend. Donec bibendum consequat erat, ac iaculis odio convallis ut.");

        CustomScrollPane scrollPane = new CustomScrollPane(textArea);
        frame.getContentPane().add(scrollPane);
        frame.setVisible(true);
    }
}