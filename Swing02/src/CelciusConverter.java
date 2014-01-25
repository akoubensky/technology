import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


/**
 * В этом приложении демонстрируется создание "полезного"
 * функционального диалога.
 * 
 * Основной класс содержит основные элементы и реализует функциональность,
 * являясь реализацией интерфейса ActionListener.
 * 
 * Copyright (c) 1997-1999 by Sun Microsystems, Inc. All Rights Reserved.
 * (русифицировано)
 */
@SuppressWarnings("serial")
public class CelciusConverter extends JFrame {
	// Текстовое поле для ввода градусов:
	JTextField tempCelsius;
	// Метки-надписи:
	JLabel celsiusLabel;
	JLabel fahrenheitLabel;
	// Кнопка для перевода из градусов Цельсия в градусы Фаренгейта:
	JButton convertTemp;

	// Конструктор
	public CelciusConverter() {
		// Создаем главное окно и панель, содержащую остальные элементы
		super("Перевод Цельсий - Фаренгейт");

		// Помещаем панель в центр окна.
		setContentPane(addWidgets());
	}

	/**
	 * Эта функция формирует все элементы управления на панели.
	 */
	private JComponent addWidgets() {
		final JPanel converterPanel = new JPanel();  // Панель, содержащаяся в окне

		// Менеджер раскладок распределяет добавляемые элементы по сетке
		// размером 2 х 2.
		LayoutManager gridLayout = new GridLayout(2, 2);
		converterPanel.setLayout(gridLayout);
		Border borderBig = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		converterPanel.setBorder(borderBig);

		// Создаем элементы.
		tempCelsius = new JTextField(5);
		celsiusLabel = new JLabel("По Цельсию", SwingConstants.LEFT);
		convertTemp = new JButton("Преобразовать...");
		fahrenheitLabel = new JLabel("По Фаренгейту", SwingConstants.LEFT);

		// Добавляем реакцию на нажатие кнопки.
		// Соответствующий "слухач", слушает событие действия с кнопкой (нажатие)
		// и выполняет дейстие - перевод значения температуры из Цельсия в
		// Фаренгейты, а затем изменяет содержимое соответствующей надписи.
		convertTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Пробуем преобразовать заданное вещественное число в градусы по Фаренгейту.
				String labelText = "По Фаренгейту: ";
				try {
					int tempFahr = (int)((Double.parseDouble(tempCelsius.getText()))
							* 1.8 + 32);
					fahrenheitLabel.setText(labelText + tempFahr);
				} catch (NumberFormatException x) {
					fahrenheitLabel.setText(labelText + "??");
				}
				((JFrame)converterPanel.getRootPane().getParent()).pack();
			}
		});

		// Добавляем все элементы в контейнер.
		converterPanel.add(tempCelsius);
		converterPanel.add(celsiusLabel);
		converterPanel.add(convertTemp);
		converterPanel.add(fahrenheitLabel);

		Border borderSmall = BorderFactory.createEmptyBorder(5,15,5,15);
		celsiusLabel.setBorder(borderSmall);
		fahrenheitLabel.setBorder(borderSmall);

		return converterPanel;
	}

	/**
	 * Основная функция, запускающая приложение.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CelciusConverter converter = new CelciusConverter();
				converter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				converter.pack();
				converter.setLocation(100, 100);
				converter.setVisible(true);
			}
		});
	}
}
