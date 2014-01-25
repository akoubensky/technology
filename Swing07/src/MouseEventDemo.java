import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;


@SuppressWarnings("serial")
/**
 * Класс представляет собой содержимое окна для регистрации событий от мыши.
 * Сама эта панель регистрирует события входа и выхода из нее курсора мыши,
 * а также нажатия кнопок мыши.
 */
public class MouseEventDemo extends JPanel {
  // Область, в которой регистрируются входы, выходы и нажатия на кнопки мыши.
  BlankArea blankArea;
  // Область вывода сообщений от событий.
  JTextArea textArea;
  // Разделитель строк (системно зависимый).
  static final String NEWLINE = System.getProperty("line.separator");

  /**
   * Основная тестовая функция, создающая окно приложения.
   * @param args - не используется.
   */
  public static void main(String[] args) {
    /* Используем системный Look and Feel */
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    // Окно создается в диспетчерском потоке.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
  
  /**
   * Создание и показ главного окна приложения.
   */
  private static void createAndShowGUI() {
    // Создаем окно приложения.
    JFrame frame = new JFrame("MouseEventDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Создаем содержимое окна и приписываем его окну.
    frame.setContentPane(new MouseEventDemo());
    
    // Упаковка и показ окна.
    frame.pack();
    frame.setVisible(true);
  }
 
  /**
   * Создание основных компонентов окна.
   */
  public MouseEventDemo() {
    // Задаем расположение элементов управления в одну колонку.
    super(new GridLayout(0,1));
    
    // Желтый прямоугольник, в котором регистрируются события от мыши.
    blankArea = new BlankArea(Color.YELLOW);
    add(blankArea);
    
    // Текстовая область, в которую выводятся сообщения о произошедших событиях.
    textArea = new JTextArea();
    textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, 12));
    textArea.setEditable(false);
    // Текстовая область должна быть "прокручиваемой", поэтому она погружается
    // в специально создаваемую для этого панель с полосами прокрутки.
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane);
    
    // Регистрируем события от мыши как в специально созданной для этого области,
    // так и во всем окне приложения (точнее, в видимой его части).
    MouseListener ml = new MyMouseListener(textArea);
    blankArea.addMouseListener(ml);
    addMouseListener(ml);
    
    // Определяем предпочтительный размер и границу для главной панели.
    setPreferredSize(new Dimension(450, 450));
    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
  }
  
  /**
   * "Слухач" для событий от мыши. События регистрируются, и
   * сообщения о них выводятся в текстувую область, передаваемую
   * конструктору "слухача".
   */
  private static class MyMouseListener extends MouseAdapter {
    // Текстовая область для вывода сообщений о событиях.
    final JTextArea textArea;
    
    /**
     * Конструктор.
     * @param ta - текстовая область для вывода сообщений.
     */
    public MyMouseListener(JTextArea ta) { textArea = ta; }
    
    /**
     * Функция для вывода сообщений.
     * @param eventDescription - текст сообщения о событии.
     * @param e - событие.
     */
    void eventOutput(String eventDescription, MouseEvent e) {
      textArea.append("Событие \"" + eventDescription + "\" случилось в "
          + e.getComponent().getClass().getName() + "." + " Bubbles: " + NEWLINE);
      // Переставляем каретку в конец области, чтобы видеть последние сообщения.
      textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // Реакция на событие входа мышиного курсора внутрь области объекта.
    public void mouseEntered(MouseEvent e) {
      eventOutput("Мышь вошла", e);
    }
    
    // Реакция на событие выхода мышиного курсора из области объекта.
    public void mouseExited(MouseEvent e) {
      eventOutput("Мышь покинула", e);
    }
    
    // Реакция на событие нажатия кнопки мыши внутри области объекта.
    public void mouseClicked(MouseEvent e) {
      eventOutput("Кнопка мыши № " + e.getButton() + " нажата "
          + e.getClickCount() + " раз", e);
    }
  }
}
