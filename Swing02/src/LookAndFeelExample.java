/*
 * Демонстрируем возможности Look & Feel.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;



/**
 * Эта программа демонстрирует возможности динамического
 * переключения Look & Feel.
 */
public class LookAndFeelExample {
  // Основное окно приложения.
  static JFrame frame;

  // Содержимое окна
  @SuppressWarnings("serial")
  static private class MyPanel extends JPanel {
    // Стандартное кросс-платформенное Look & Feel
    static final String metal= "Metal";
    static final String metalClassName = "javax.swing.plaf.metal.MetalLookAndFeel";

    // Современное кросс-платформенное Look & Feel
    static final String nimbus= "Nimbus";
    static final String nimbusClassName = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    // Java-стандарт - наследие операционной системы Motif.
    static final String motif = "Motif";
    static final String motifClassName = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

    // Окна "как в Windows"
    static final String windows = "Windows";
    static final String windowsClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    final JPanel upper;
    final JButton button;
    final JLabel label;
    final JRadioButton metalButton, nimbusButton, motifButton, windowsButton;
    
    int number = 0;
    final String labelFormat = "Число нажатий кнопки: %d";

    public MyPanel() {
      button = new JButton("Hello, world");
      button.setMnemonic('h');
      
      upper = new JPanel();
      label = new JLabel(String.format(labelFormat, number));

      // Эти радио-кнопки - для переключения Look & Feel
      metalButton = new JRadioButton(metal);
      metalButton.setMnemonic('o'); 
      metalButton.setActionCommand(metalClassName);
      System.out.println(metalButton.getActionCommand());
  
      nimbusButton = new JRadioButton(nimbus);
      nimbusButton.setMnemonic('n'); 
      nimbusButton.setActionCommand(nimbusClassName);
      System.out.println(nimbusButton.getActionCommand());
  
      motifButton = new JRadioButton(motif);
      motifButton.setMnemonic('m'); 
      motifButton.setActionCommand(motifClassName);
      System.out.println(motifButton.getActionCommand());
  
      windowsButton = new JRadioButton(windows);
      windowsButton.setMnemonic('w'); 
      windowsButton.setActionCommand(windowsClassName);
      System.out.println(windowsButton.getActionCommand());
  
      // Собираем радио-кнопки в группу.
      ButtonGroup group = new ButtonGroup();
      group.add(metalButton);
      group.add(nimbusButton);
      group.add(motifButton);
      group.add(windowsButton);
  
      // Создаем реакции на переключение кнопок.
      RadioListener myListener = new RadioListener();
      metalButton.addActionListener(myListener);
      nimbusButton.addActionListener(myListener);
      motifButton.addActionListener(myListener);
      windowsButton.addActionListener(myListener);

      // Создаем реакцию на нажатие "большой кнопки"      
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          label.setText(String.format(labelFormat, ++number));
        }
      });

      // Формируем панель
      upper.add(button);
      upper.add(label);
      add(upper);
      add(metalButton);
      add(nimbusButton);
      add(motifButton);
      add(windowsButton);
    }


    /** Этот класс определяет поведение программы при нажатии
     * радио-кнопки. 
     */
    class RadioListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        // Имя каждого Look & Feel присвоено кнопке в качестве ее ActionCommand
        String lnfName = e.getActionCommand();

        try {
          // Пытаемся установить заданный Look & Feel
          UIManager.setLookAndFeel(lnfName);
          // Обновляем расположение элементов в окне
          SwingUtilities.updateComponentTreeUI(frame.getContentPane());
          // Обновляем раскладку (layout).
          frame.pack();
        } catch (Exception exc) {
          // Не удалось установить новый Look & Feel, выключаем кнопку
          JRadioButton button = (JRadioButton)e.getSource();
          button.setEnabled(false);
          updateState();
          System.err.println("Could not load LookAndFeel: " + lnfName);
        }
      }
    }

    // Устанавливаем текущую радио-кнопку в соответствии
    // с установленным Look & Feel.
    public void updateState() {
      String lnfName = UIManager.getLookAndFeel().getClass().getName();
      if (lnfName.indexOf(metal) >= 0) {
        metalButton.setSelected(true);
      } else if (lnfName.indexOf(nimbus) >= 0) {
        nimbusButton.setSelected(true);
      } else if (lnfName.indexOf(windows) >= 0) {
        windowsButton.setSelected(true);
      } else if (lnfName.indexOf(motif) >= 0) {
        motifButton.setSelected(true);
      } else {
        System.err.println("Неизвестный Look & Feel: " + lnfName);
      }
    }
  }

  /**
   * Основная функция, запускающая все приложение
   */
  public static void main(String s[]) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
          e.printStackTrace();
        }
        MyPanel panel = new MyPanel();
        
        frame = new JFrame("Look & Feel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        
        panel.updateState();
      }
    });
  }
}
