/**
 * Решение задачи о расстановке ферзей на квадратной шахматной доске произвольного размера.
 */
public class Queens {
    // Размер стандартной доски
	static int QUEENS = 8;

    /**
     * Попытка найти правильную расстановку ферзей из заданной исходной позиции.
     * @param src   Исходная позиция
     * @return      Правильная расстановка, если она существует, иначе - null.
     */
    private static Position correct(Position src) {
		int x = src.set;
        // Если все ферзи уже стоят, то мы нашли правильную расстановку.
		if (x == QUEENS) return src;

        // Пытаемся поставить очередного ферзя на свободную горизонталь
        // и найти правильную расстановку с этим новым поставленным ферзем.
		for (int y = 0; y < QUEENS; ++y) {
			if (src.maySet.test(x+1, y)) {
                // Удалось поставить ферзя, не конфликтующего с уже поставленными.
                // Создаем новую позицию.
				Position newPos = src.next(y);
                // Пытаемся найти правильную расстановку из новой позиции.
				Position finalPos = correct(newPos);
				if (finalPos != null) {
                    // Нашли!
					return finalPos;
                }
			}
		}
        // Нового ферзя не удалось никуда поставить...
		return null;
	}

    /**
     * Запуск функции поиска правильной расстановки ферзей.
     * @param args  Параметр программы - размер доски (8, если не задан).
     */
	public static void main(String[] args) {
        // Анализ параметра, если он есть
		if (args.length > 0) {
			try {
				QUEENS = Integer.parseInt(args[0]);
			} catch(NumberFormatException x) {
				System.out.println("Wrong parameter");
				return;
			}
		}

        // Пытаемся построить правильную позицию из начальной пустой.
		Position finalPos = correct(Position.EMPTY);
		if (finalPos != null) {
            // Расстановка найдена
			finalPos.printPos.accept(QUEENS);
		} else {
            // Расстановка не найдена
			System.out.println("No position!");
		}
	}
}
