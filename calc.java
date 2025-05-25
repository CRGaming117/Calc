
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class calc {
  public static ArrayList<JButton> buttons;

  public static void main(String[] args) {
    JFrame window = new JFrame("Calc App");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Color bg = Color.lightGray;

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

    String[] modes = { "Standard", "Scientific", "Graphic", "Sequences" };
    JComboBox modeBox = new JComboBox(modes);
    modeBox.setPreferredSize(new Dimension(430, 25));

    JTextField display = new JTextField();
    display.setHorizontalAlignment(SwingConstants.RIGHT);
    display.setFont(new Font("Arial", Font.PLAIN, 70));
    display.setPreferredSize(new Dimension(430, 150));

    Font FONT = new Font("Arial", Font.BOLD, 30);
    Dimension SIMPLE = new Dimension(100, 70);
    Dimension TALL = new Dimension(100, 145);
    Dimension LONG = new Dimension(205, 70);
    String[] icons = { "%", "/", "*", "-",
        "7", "8", "9",
        "4", "5", "6",
        "1", "2", "3",
        "0", ".",
        "+", "=" };
    buttons = new ArrayList<JButton>();
    for (String icon : icons) {
      JButton b = new JButton(icon);
      b.setPreferredSize((icon == "+" || icon == "=") ? TALL : (icon == "0") ? LONG : SIMPLE);
      b.setFont(FONT);
      buttons.add(b);
    }

    JPanel topCont = new JPanel();
    topCont.setPreferredSize(new Dimension(430, 80));
    topCont.setBackground(bg);

    JPanel plusCont = new JPanel();
    plusCont.setPreferredSize(new Dimension(320, 155));
    plusCont.setBackground(bg);

    JPanel bottCont = new JPanel();
    bottCont.setPreferredSize(new Dimension(320, 155));
    bottCont.setBackground(bg);

    for (int i = 0; i < 4; i++)
      topCont.add(buttons.get(i));
    for (int i = 4; i < 10; i++)
      plusCont.add(buttons.get(i));
    for (int i = 10; i < 15; i++)
      bottCont.add(buttons.get(i));

    content.add(modeBox);
    content.add(display);

    content.add(topCont);
    content.add(plusCont);
    content.add(buttons.get(15)); // +
    content.add(bottCont);
    content.add(buttons.get(16)); // =

    window.setResizable(false);
    window.getContentPane().add(content);
    window.pack();
    window.setVisible(true);
  }

  private class StandardPanel extends JPanel {
    public StandardPanel() {

    }
  }
}
