import javax.swing.*;        

// ��� ����� ������� ����������, ���������������
// ������� ����������� Swing.
public class HelloWorldSwing {

	public static void createAndShowGUI() {
		// ������� ������� ����
		JFrame frame = new JFrame("Hello world");

		// ������� ����� � ���� ����
		final JLabel label = new JLabel("Hello, World!");
		frame.getContentPane().add(label);

		// ��� �������� ����������� ����������� � ������ Swing-����������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { createAndShowGUI(); }
		});
	}
}