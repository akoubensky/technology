import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelloWorld {

	public static void main(String[] args) {
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
}
