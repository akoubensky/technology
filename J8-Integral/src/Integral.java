import java.util.function.DoubleFunction;

/**
 * Вычисление интеграла методом трапеций.
 * Используются средства Java8 для представления функций.
 */
public class Integral {
    /**
     * Алгоритм вычисления интеграла заданной функции методом трапеций с заданной точностью.
     *
     * @param func  Заданная функция
     * @param left  Левый конец промежутка интегрирования
     * @param right Правый конец промежутка интегрирования
     * @param eps   Точность вычислений
     * @return Приближенное значение интеграла
     */
    public static double integral(DoubleFunction<Double> func, double left, double right, double eps) {
        // Шаг интегральной суммы (уменьшается вдвое на каждом шаге алгоритма)
        double step = right - left;
        // Промежуточная сумма (уточняется на каждом шаге алгоритма)
        double sum = (func.apply(left) + func.apply(right)) / 2 * step;

        for (; ; ) {
            double x = left + step / 2;   // Абсцисса
            double delta = 0;           // Добавочная сумма за счет новых промежуточных точек
            while (x < right) {
                delta += func.apply(x);
                x += step;
            }
            step /= 2;
            if (Math.abs(delta * step - sum / 2) < eps) {
                return sum;
            }
            sum = delta * step + sum / 2;
        }
    }

    /**
     * Проверка: Интеграл от косинуса на промежутке от 0 до pi/2 должен быть равен единице.
     *
     * @param args  Не используется
     */
    public static void main(String[] args) {
        System.out.println(integral(Math::cos, 0, Math.PI / 2, 0.000001));
    }

}
