
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calc extends JFrame implements ItemListener {
  static JFrame window;
  static JComboBox<String> modeBox;
  static JPanel content;
  static StandardPanel standardPanel;

  public static void main(String[] args) {
    Color bg = Color.black; // Background
    Color fg = new Color(255, 75, 0); // Foreground

    window = new JFrame("Calc App");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    content = new JPanel();
    content.setPreferredSize(new Dimension(450, 600));
    content.setBackground(bg);

    calc c = new calc();

    String[] modes = { "Standard", "Scientific", "Graphing", "Sequence" };
    modeBox = new JComboBox<String>(modes);
    modeBox.setPreferredSize(new Dimension(430, 25));
    modeBox.setBackground(bg);
    modeBox.setForeground(fg);
    modeBox.setBorder(BorderFactory.createLineBorder(fg));
    modeBox.addItemListener(c);

    standardPanel = new StandardPanel(bg, fg);
    content.add(modeBox);

    content.add(standardPanel);

    window.getContentPane().add(content);
    window.pack();
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    SwingUtilities.invokeLater(() -> {
      standardPanel.display.requestFocusInWindow();
      standardPanel.display.selectAll();
    });

  }

  public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == modeBox) {
      switch (modeBox.getSelectedIndex()) {
        // Standard
        case 0:
          standardPanel.setVisible(true);
          break;
        // Scientific
        case 1:
          standardPanel.setVisible(false);
          break;
        // Graphing
        case 2:
          standardPanel.setVisible(false);
          break;
        // Sequence
        case 3:
          standardPanel.setVisible(false);
          break;

      }
    }
  }
}
