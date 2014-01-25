import javax.swing.*;        

// Это очень простое приложение, демонстрирующее
// базовые возможности Swing.
public class HelloWorldSwing {

	public static void createAndShowGUI() {
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { createAndShowGUI(); }
		});
	}
}