import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Will figure this out later
import org.python.util.PythonInterpreter;
import org.python.core.PyObject;

public class StandardPanel extends JPanel {

  private JTextField display;
  private ArrayList<JButton> buttons;

  public StandardPanel(Color bg) {
    Color pbg = Color.black;
    Color fg = new Color(255, 75, 0);

    this.setPreferredSize(new Dimension(450, 570));
    this.setBackground(bg);

    display = new JTextField();
    display.setHorizontalAlignment(SwingConstants.RIGHT);
    display.setFont(new Font("Arial", Font.PLAIN, 70));
    display.setPreferredSize(new Dimension(430, 150));
    display.setBackground(pbg);
    display.setBorder(BorderFactory.createLineBorder(fg));
    display.setForeground(Color.lightGray);
    display.addActionListener(new TextListener());
    display.addKeyListener(new KeyFilter());

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
      b.setBackground(pbg);
      b.setForeground(fg);
      b.setBorder(BorderFactory.createLineBorder(fg));
      b.addActionListener(new ButtonListener());
      buttons.add(b);
    }

    JPanel topCont = new JPanel();
    topCont.setPreferredSize(new Dimension(430, 75));
    topCont.setBackground(bg);

    JPanel plusCont = new JPanel();
    plusCont.setPreferredSize(new Dimension(320, 150));
    plusCont.setBackground(bg);

    JPanel bottCont = new JPanel();
    bottCont.setPreferredSize(new Dimension(320, 150));
    bottCont.setBackground(bg);

    for (int i = 0; i < 4; i++)
      topCont.add(buttons.get(i));
    for (int i = 4; i < 10; i++)
      plusCont.add(buttons.get(i));
    for (int i = 10; i < 15; i++)
      bottCont.add(buttons.get(i));

    this.add(display);
    this.add(topCont);
    this.add(plusCont);
    this.add(buttons.get(15)); // +
    this.add(bottCont);
    this.add(buttons.get(16)); // =

    this.addKeyListener(new KL());
    this.setFocusable(true);
  }

  String calculate(String exp) {
    try {
      PythonInterpreter interpreter = new PythonInterpreter();
      interpreter.set("display", display);
      PyObject result = interpreter.eval(exp);
      return result.toString();
    } catch (Exception e) {
      return ((display.getText().isEmpty() || display.getText().equals("Error")) ? "" : "Error");
    }
  }

  public class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      for (JButton b : buttons) {
        if (e.getSource() == b) {
          if (b.getText() != "=")
            display.setText(display.getText() + b.getText());
          else
            display.setText(calculate(display.getText()));
        }
      }
    }
  }

  public class TextListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == display) {
        // System.out.println("Enter pressed");
        display.setText(calculate(display.getText()));
      }

    }
  }

  public class KeyFilter implements KeyListener {
    public void keyPressed(KeyEvent e) {
      boolean isNum = (e.getKeyChar() >= '0' && e.getKeyChar() <= '9');
      boolean isSymbol = (e.getKeyChar() == '+' || e.getKeyChar() == '-' || e.getKeyChar() == '*'
          || e.getKeyChar() == '/' || e.getKeyChar() == '%' || e.getKeyChar() == '.');
      boolean isAction = (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER);
      if (isNum || isSymbol || isAction)
        display.setEditable(true);
      else
        display.setEditable(false);

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
  }

  public class KL implements KeyListener {
    public void keyPressed(KeyEvent e) {
      // System.out.println("" + e.getKeyChar() + " " + (int) e.getKeyChar() + " " +
      // e.getKeyCode());
      String t = display.getText();

      if (e.getKeyCode() == KeyEvent.VK_F9)
        display.setText(t + "-");

      // if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
      // display.setText(t.substring(0, t.length() - 1));

      if (e.getKeyCode() == KeyEvent.VK_DELETE)
        display.setText("");

      if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
  }
}
