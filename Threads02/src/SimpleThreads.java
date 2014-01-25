/**
 * ������ ��������� ���������:
 * t.join(), t.join(millis), t.interrupt()
 * t.getName().
 * ��� ���� Thread.interrupted() - ������ interrupted ������,
 * t.isInterrupted() - ������ ��������� ������.
 */
public class SimpleThreads {

	// ������� ���������, ���������� ������ ����
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	private static class MessageLoop implements Runnable {
		public void run() {
			String importantInfo[] = {
					"���� ������� ����,",
					"� ������ ������� ����,",
					"� ������ ������� ����,",
					"� ���� ������� ����."
			};
			try {
				for (int i = 0; i < importantInfo.length; i++) {
					// �������� �� 4 �������
					Thread.sleep(4000);
					// �������� ��������� ���������
					threadMessage(importantInfo[i]);
				}
			} catch (InterruptedException e) {
				threadMessage("� ���� ���, ��� ���!");
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {


		// �������� � ������������� ����� ���, ��� �� ������� ���� MessageLoop
		long patience = 1000 * 14;

		threadMessage("Starting MessageLoop thread");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop(), "��������");
		t.start();

		threadMessage("����, ���� ���� MessageLoop ����������.");
		// ����, ���� ���� MessageLoop ����������.
		while (t.isAlive()) {
			threadMessage("���� ����...");
			// ���� �������� 1 ������� ��������� ���� MessageLoop.
			t.join(1000);
			if (((System.currentTimeMillis() - startTime) > patience) &&
					t.isAlive()) {
				threadMessage("������ �����!");
				t.interrupt();
				// ������ ��� �������...
				t.join();
			}

		}
		threadMessage("��, �������!");
	}
}
