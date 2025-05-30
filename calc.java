
import javax.swing.*;
import java.awt.*;

public class calc {
  public static void main(String[] args) {
    JFrame window = new JFrame("Calc App");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Color bg = Color.black;

    JPanel content = new JPanel();
    content.setPreferredSize(new Dimension(450, 600));
    content.setBackground(bg);

    /*
     * JPanel modePanel = new JPanel();
     * modePanel.setPreferredSize(new Dimension(w, 25));
     * modePanel.setBackground(Color.white);
     * 
     * JLabel modeLabel = new JLabel(mode);
     * modeLabel.setHorizontalAlignment(SwingConstants.LEFT);
     * modeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
     */

    String[] modes = { "Standard", "Scientific", "Graphing", "Sequence" };
    JComboBox modeBox = new JComboBox(modes);
    modeBox.setPreferredSize(new Dimension(430, 25));

    content.add(modeBox);
    content.add(new StandardPanel(bg));

    window.getContentPane().add(content);
    window.pack();
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
}
