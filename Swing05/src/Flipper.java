
import java.util.List;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * ����� ������������� ������ ����������, ����������� ���������������� ���������,
 * � ������� ������������ ������������ ������� ������.
 */
@SuppressWarnings("serial")
public class Flipper extends JFrame {

  // ����� ��� ������������� ��������� ��������, 
  // ������������� ������� ������� - ���������� �������� ������.
  private static class FlipPair {
    final long heads, total;
    FlipPair(long heads, long total) {
      this.heads = heads;
      this.total = total;
    }
  }

  // ��������� ����, �������������� ���������� ��������� �����,
  // ����� ����� �������� ������ � ����������� ���������� �� �����������.
  private final JTextField headsText, totalText, devText;
  // "���������" ������� ��������� �����.
  private final Border border =
    BorderFactory.createLoweredBevelBorder();
  // ������ ��� ������ � ���������� ������.
  private final JButton startButton, stopButton;
  // ������, �������������� ������� ������.
  private FlipTask flipTask;

  // �������� ���������� ���� � ���������� ��� � �������� ����.
  private JTextField createTextField() {
    JTextField t = new JTextField(20);
    t.setEditable(false);
    t.setHorizontalAlignment(JTextField.RIGHT);
    t.setBorder(border);
    getContentPane().add(t);
    return t;
  }

  // �������� ������ � �������� �������� � ����������
  // � ���������� �� � �������� ����.
  private JButton createButton(String caption, String command) {
    JButton b = new JButton(caption);
    b.setActionCommand(command);
    getContentPane().add(b);
    return b;
  }

  // ����������� ���� ������� � ��������� ���� ����������.
  public Flipper() {
    super("Flipper");

    getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

    // ������� ��������� ����.
    headsText = createTextField();
    totalText = createTextField();
    devText = createTextField();

    // ������� ������.
    startButton = createButton("������!", "start");
    startButton.addActionListener(new ActionListener() {
      /**
       * ������� �� ������� ������ - ������ ��������.
       * @param e - ������� ������� ������.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        // ��������� ������� ������.
        (flipTask = new FlipTask()).execute();
      }
    });
    stopButton = createButton("����", "stop");
    stopButton.setEnabled(false);
    stopButton.addActionListener(new ActionListener() {
      /**
       * ������� �� ������� ������ - ��������� ��������.
       * @param e - ������� ������� ������.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        // ������������� ������� ������.
        flipTask.cancel(true);
        flipTask = null;
      }
    });
  }

  // ����, �������������� ������������ ������� ������.
  private class FlipTask extends SwingWorker<Void, FlipPair> {
    /**
     * ������� ������
     */
    @Override
    protected Void doInBackground() {
      long heads = 0; // ����� �������� "������".
      long total = 0; // ����� ��������.
      Random random = new Random(); // ������ ��������������� �����.
      while (!isCancelled()) {
        // "�������� ������"
        total++;
        if (random.nextBoolean()) {
          heads++;
        }
        // ������� ����� "���������".
        publish(new FlipPair(heads, total));
      }
      // ������������� ��������� ������ �� ����������.
      return null;
    }

    /**
     * ��������� ������������� �����������
     * @param pairs - ������ ��������� �����������
     */
    @Override
    protected void process(List<FlipPair> pairs) {
      // ��������� ������ ��������� ���������
      FlipPair pair = pairs.get(pairs.size() - 1);
      // ���������� ���� ��������� � ���� ��������� ����.
      headsText.setText(String.format("%d", pair.heads));
      totalText.setText(String.format("%d", pair.total));
      // ������ ��� ������������ ���������� - ������������ �
      // ������������� ������ � ������� ������� ��������.
      devText.setText(String.format("%14.10f",
          ((double) pair.heads)/((double) pair.total) - 0.5));
    }
  }

  /**
   * ������� �������� ������� ��������� ���� ����������.
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // ������ look and feel.
        try {
          UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}

        Flipper flipper = new Flipper();

        flipper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� ��������� ���� � ��� �����.
        flipper.pack();
        flipper.setVisible(true);
      }
    });
  }
}
