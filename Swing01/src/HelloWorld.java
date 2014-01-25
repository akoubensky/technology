import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelloWorld {

	public static void main(String[] args) {
		// Создаем главное окно
		JFrame frame = new JFrame("Hello world");

		// Создаем метку в этом окне
		final JLabel label = new JLabel("Hello, World!");
		frame.getContentPane().add(label);

		// Эти операции исполняются практически в каждом Swing-приложении
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
