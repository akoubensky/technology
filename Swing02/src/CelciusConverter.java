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
 * � ���� ���������� ��������������� �������� "���������"
 * ��������������� �������.
 * 
 * �������� ����� �������� �������� �������� � ��������� ����������������,
 * ������� ����������� ���������� ActionListener.
 * 
 * Copyright (c) 1997-1999 by Sun Microsystems, Inc. All Rights Reserved.
 * (��������������)
 */
@SuppressWarnings("serial")
public class CelciusConverter extends JFrame {
	// ��������� ���� ��� ����� ��������:
	JTextField tempCelsius;
	// �����-�������:
	JLabel celsiusLabel;
	JLabel fahrenheitLabel;
	// ������ ��� �������� �� �������� ������� � ������� ����������:
	JButton convertTemp;

	// �����������
	public CelciusConverter() {
		// ������� ������� ���� � ������, ���������� ��������� ��������
		super("������� ������� - ���������");

		// �������� ������ � ����� ����.
		setContentPane(addWidgets());
	}

	/**
	 * ��� ������� ��������� ��� �������� ���������� �� ������.
	 */
	private JComponent addWidgets() {
		final JPanel converterPanel = new JPanel();  // ������, ������������ � ����

		// �������� ��������� ������������ ����������� �������� �� �����
		// �������� 2 � 2.
		LayoutManager gridLayout = new GridLayout(2, 2);
		converterPanel.setLayout(gridLayout);
		Border borderBig = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		converterPanel.setBorder(borderBig);

		// ������� ��������.
		tempCelsius = new JTextField(5);
		celsiusLabel = new JLabel("�� �������", SwingConstants.LEFT);
		convertTemp = new JButton("�������������...");
		fahrenheitLabel = new JLabel("�� ����������", SwingConstants.LEFT);

		// ��������� ������� �� ������� ������.
		// ��������������� "������", ������� ������� �������� � ������� (�������)
		// � ��������� ������� - ������� �������� ����������� �� ������� �
		// ����������, � ����� �������� ���������� ��������������� �������.
		convertTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������� ������������� �������� ������������ ����� � ������� �� ����������.
				String labelText = "�� ����������: ";
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

		// ��������� ��� �������� � ���������.
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
	 * �������� �������, ����������� ����������.
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
