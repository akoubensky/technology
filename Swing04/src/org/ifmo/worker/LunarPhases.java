package org.ifmo.worker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class LunarPhases extends JFrame {
  final static int NUM_IMAGES = 8;  // Число картинок
  final static int START_INDEX = 3; // Номер начальной картинки

  // Массив картинок
  ImageIcon[] images;
  
  // Основные контейнеры
  JPanel mainPanel, selectPanel, displayPanel;

  // Основные элементы управления
  JComboBox phaseChoices = null;
  JLabel phaseIconLabel = null;

  /**
   *  Конструктор создает все панели и элементы управления. 
   */
  public LunarPhases() {
    super("Фазы луны");
  }
  
  public void initImages() {
    ImageIcon emptyIcon = new ImageIcon("images/image0.jpg");
    for (int i = 0; i < NUM_IMAGES; i++) {
      images[i] = emptyIcon;
    }
  }
  
  public JPanel createContent() {
    images = new ImageIcon[NUM_IMAGES];
    
    // Создаем основные панели.
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

    selectPanel = new JPanel();
    selectPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Выберите фазу луны"), 
        BorderFactory.createEmptyBorder(5,5,5,5)));

    displayPanel = new JPanel();
    displayPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Изображение луны"), 
        BorderFactory.createEmptyBorder(5,5,5,5)));
  
    // Создаем основные элементы управления.
    phaseIconLabel = new JLabel();
    phaseIconLabel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(0,0,10,0),
        BorderFactory.createCompoundBorder(
            BorderFactory.createLoweredBevelBorder(),
            BorderFactory.createEmptyBorder(5,5,5,5))));
    initImages();
    phaseIconLabel.setIcon(images[START_INDEX]);
    
    displayPanel.add(phaseIconLabel);
    
    String[] phases = { 
        "Новолуние", "Молодая", "Первая четверть", "Почти полная", 
        "Полная", "Начало убывания", "Третья четверть", "Старая" };
    phaseChoices = new JComboBox(phases);
    phaseChoices.setSelectedIndex(START_INDEX);
    phaseChoices.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        // Обновляем картинку.
        phaseIconLabel.setIcon(images[phaseChoices.getSelectedIndex()]);
      }
    });
    
    selectPanel.add(phaseChoices);
    
    // Добавляет элементы управления на панели.
    mainPanel.add(displayPanel);
    mainPanel.add(selectPanel);
    
    new ImageLoader().execute();
    
    return mainPanel;
  }
  
  // Этот класс реализует отдельно выполняющуюся фоновую работу
  // по загрузке картинок.
  private class ImageLoader extends SwingWorker<ImageIcon[], Void> {
    /**
     * Определяет фоновый процесс загрузки картинок.
     * @return массив загруженных картинок.
     */
    protected ImageIcon[] doInBackground() {
      ImageIcon[] res = new ImageIcon[NUM_IMAGES];
      // Загружаем элементы и помещаем их в массив ImageIcon.
      for (int i = 0; i < NUM_IMAGES; i++) {
        // Искусственная задержка для создания эффекта длительной работы.
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {}
        
        String imageName = "images/image" + i + ".jpg";
        System.out.println("getting image: " + imageName);
    
        res[i] = new ImageIcon(imageName);
      }
      return res;
    }
 
    /**
     * Передает результат работы в основную программу.
     */
    protected void done() {
      try {
        // Передаем загруженные картинки в основное приложение.
        images = get();
        // Обновляем текущую картинку.
        phaseIconLabel.setIcon(images[phaseChoices.getSelectedIndex()]);
      } catch (InterruptedException e) {
      } catch (ExecutionException e) {
      }
    }
  }

  public static void main(String[] args) {
    
    class Creator implements Runnable {
      final LunarPhases mainFrame = new LunarPhases();
      
      public void run() {
        mainFrame.setContentPane(mainFrame.createContent());
        
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
      }
    };

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception e) {}
    
    SwingUtilities.invokeLater(new Creator());
  }
}
