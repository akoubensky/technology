package org.ifmo.array;

import java.util.ArrayList;

/**
 * Класс представляет расстановку некоторого числа ферзей на квадратной шахматной доске.
 * Предполагается, что все ферзи занимают несколько первых горизонталей, причем на каждой
 * горизонтали находится ровно один ферзь. Позиция &quot;правильная&quot;, если никакие два
 * ферзя не находятся ни на одной горизонтали, ни на одной вертикали и ни на одной диагонали.
 * Мы рассматриваем только правильные позиции.
 */
public class Position {
	// Массив, представляющий расстановку ферзей на первых set горизонталях
	private ArrayList<Integer> board = new ArrayList<>();
	
    // Пустая позиция
	public static Position EMPTY = new Position();
	
    /**
     * Конструктор пустой позиции
     */
	private Position() {}
	
	/**
	 * Количество поставленных ферзей.
	 * @return Число горизонталей в позиции.
	 */
	public int size() { return board.size(); }
	
	/**
     * Проверяет, можно ли поставить ферзя на одну из свободных горизонталей так, чтобы он
     * не "конфликтовал" с уже поставленными ферзями.
	 * @param row	Горизонталь, на которой будет стоять новый ферзь.
	 * @param col	Вертикаль, на которой будет стоять новый ферзь.
	 * @return
	 */
	public boolean maySet(int row, int col) {
		for (int r = 0; r < board.size(); r++) {
			int c = board.get(r);
			if (col == c || Math.abs(col - c) == row - r)
				return false;
		}
		return true;
	}
	
	/**
     * Печатает позиции ферзей на поставленных горизонталях, считая, что размер доски
     * задан числом горизонталей в позиции.
	 */
	public void printPos() {
		int width = board.size();
		for (int r = 0; r < width; r++) {
			int c = board.get(r);
			for (int p = 0; p < c; p++) {
				System.out.print(" .");
			}
			System.out.print(" *");
			for (int p = c + 1; p < width; p++) {
				System.out.print(" .");
			}
			System.out.println();
		}
	}
	
    /**
     * Генерирует новую позицию, ставя ферзя на первую свободную горизонталь в позицию,
     * заданную аргументом.
     * @param y    Номер вертикали, на которую ставится новый ферзь.
     * @return     Новая позиция, в которой на одного ферзя больше, чем в исходной.
     */
	public Position next(int newCol) {
		Position newPos = new Position();
		newPos.board.addAll(board);
		newPos.board.add(newCol);
		return newPos;
	}
}
