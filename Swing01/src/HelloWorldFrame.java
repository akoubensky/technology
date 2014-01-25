import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class HelloWorldFrame extends JFrame {
	public HelloWorldFrame() { super("Hello world"); }

	public void createAndShowGUI() {
		final JLabel label = new JLabel("Hello, World!");
		getContentPane().add(label);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { (new HelloWorldFrame()).createAndShowGUI(); }
		});
	}
}
