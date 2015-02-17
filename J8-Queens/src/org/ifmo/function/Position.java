package org.ifmo.function;
import java.util.function.BiPredicate;
import java.util.function.IntConsumer;

/**
 * Класс представляет расстановку некоторого числа ферзей на квадратной шахматной доске.
 * Предполагается, что все ферзи занимают несколько первых горизонталей, причем на каждой
 * горизонтали находится ровно один ферзь. Позиция &quot;правильная&quot;, если никакие два
 * ферзя не находятся ни на одной горизонтали, ни на одной вертикали и ни на одной диагонали.
 * Мы рассматриваем только правильные позиции.
 */
public class Position {
    // Количество установленных ферзей
	public final int set;
    // Проверяет, можно ли поставить ферзя на одну из свободных горизонталей так, чтобы он
    // не "конфликтовал" с уже поставленными ферзями.
	public final BiPredicate<Integer, Integer> maySet;
    // Печатает позиции ферзей на поставленных горизонталях, считая, что размер доски задан аргументом.
	public final IntConsumer printPos;

    // Пустая позиция
	public static final Position EMPTY = new Position();

    /**
     * Конструктор пустой позиции
     */
	private Position() {
		maySet = (x, y) -> true;   // Ферзя можно ставить на любую клетку
		printPos = n -> {};        // Печать пустой позиции не делает ничего.
		set = 0;                   // Всего установлено 0 ферзей
	}

    /**
     * Конструктор позиции с заданными параметрами.
     * @param set       число расставленных ферзей
     * @param maySet    функция проверки возможности установки нового ферзя
     * @param printPos  функция печати позиции
     */
	private Position(int set, BiPredicate<Integer, Integer> maySet, IntConsumer printPos) {
		this.set = set;
		this.maySet = maySet;
		this.printPos = printPos;
	}

    /**
     * Генерирует новую позицию, ставя ферзя на первую свободную горизонталь в позицию, заданную аргументом.
     * @param y    Номер вертикали, на которую ставится новый ферзь.
     * @return     Новая позиция, в которой на одного ферзя больше, чем в исходной.
     */
	public Position next(int y) {
		return new Position(
				set + 1,
				maySet.and((x1, y1) -> y1 != y && Math.abs(y1-y) != x1 - (set+1)),
				printPos.andThen(n -> printLine(n, y))
		);
	}

    /**
     * Печатает одну строку позиции.
     * @param n   Предполагаемый размер доски (число вертикалей)
     * @param p   Позиция ферзя в заданной горизонтали
     */
	private static void printLine(int n, int p) {
		while (--p >= 0) {
			System.out.print(". ");
			n--;
		}
		System.out.print("* "); n--;
		while (--n >= 0) {
			System.out.print(". ");
		}
		System.out.println();
	}
}
