
import java.util.List;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Класс демонстрирует работу приложения, содержащего пользовательский интерфейс,
 * в котором производится параллельная фоновая работа.
 */
@SuppressWarnings("serial")
public class Flipper extends JFrame {

  // Класс для представления основного элемента, 
  // производимого фоновой работой - результаты бросания монеты.
  private static class FlipPair {
    final long heads, total;
    FlipPair(long heads, long total) {
      this.heads = heads;
      this.total = total;
    }
  }

  // Текстовые поля, представляющие количество выпадений герба,
  // общее число бросаний монеты и стандартное отклонение от вероятности.
  private final JTextField headsText, totalText, devText;
  // "Украшение" границы текстовых полей.
  private final Border border =
    BorderFactory.createLoweredBevelBorder();
  // Кнопки для старта и завершения работы.
  private final JButton startButton, stopButton;
  // Объект, представляющий фоновую задачу.
  private FlipTask flipTask;

  // Создание текстового поля и добавление его в основное окно.
  private JTextField createTextField() {
    JTextField t = new JTextField(20);
    t.setEditable(false);
    t.setHorizontalAlignment(JTextField.RIGHT);
    t.setBorder(border);
    getContentPane().add(t);
    return t;
  }

  // Создание кнопки с заданной командой и заголовком
  // и добавление ее в основное окно.
  private JButton createButton(String caption, String command) {
    JButton b = new JButton(caption);
    b.setActionCommand(command);
    getContentPane().add(b);
    return b;
  }

  // Конструктор окна создает и наполняет окно приложения.
  public Flipper() {
    super("Flipper");

    getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

    // Создаем текстовые поля.
    headsText = createTextField();
    totalText = createTextField();
    devText = createTextField();

    // Создаем кнопки.
    startButton = createButton("Начать!", "start");
    startButton.addActionListener(new ActionListener() {
      /**
       * Реакция на нажатие кнопки - запуск флиппера.
       * @param e - событие нажатия кнопки.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        // Запускаем фоновую работу.
        (flipTask = new FlipTask()).execute();
      }
    });
    stopButton = createButton("Стоп", "stop");
    stopButton.setEnabled(false);
    stopButton.addActionListener(new ActionListener() {
      /**
       * Реакция на нажатие кнопки - остановка флиппера.
       * @param e - событие нажатия кнопки.
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        // останавливаем фоновую работу.
        flipTask.cancel(true);
        flipTask = null;
      }
    });
  }

  // Клас, представляющий производимую фоновую работу.
  private class FlipTask extends SwingWorker<Void, FlipPair> {
    /**
     * Фоновая работа
     */
    @Override
    protected Void doInBackground() {
      long heads = 0; // Число выпавших "гербов".
      long total = 0; // Всего бросаний.
      Random random = new Random(); // Датчик псевдослучайных чисел.
      while (!isCancelled()) {
        // "бросание монеты"
        total++;
        if (random.nextBoolean()) {
          heads++;
        }
        // Создаем новый "результат".
        publish(new FlipPair(heads, total));
      }
      // Окончательный результат никого не интересует.
      return null;
    }

    /**
     * Обработка промежуточных результатов
     * @param pairs - список очередных результатов
     */
    @Override
    protected void process(List<FlipPair> pairs) {
      // Интересен только последний результат
      FlipPair pair = pairs.get(pairs.size() - 1);
      // Записываем этот результат в поля основного окна.
      headsText.setText(String.format("%d", pair.heads));
      totalText.setText(String.format("%d", pair.total));
      // Формат для стандартного отклонения - вещественное с
      // фиксированной точкой и десятью знаками точности.
      devText.setText(String.format("%14.10f",
          ((double) pair.heads)/((double) pair.total) - 0.5));
    }
  }

  /**
   * Тествая основная функция запускает окно приложения.
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // Задаем look and feel.
        try {
          UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {}

        Flipper flipper = new Flipper();

        flipper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Упаковка элементов окна и его показ.
        flipper.pack();
        flipper.setVisible(true);
      }
    });
  }
}
