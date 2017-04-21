import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Построение потока над упорядоченным деревом поиска.
 * Дерево представлено простейшей реализацией без попыток балансировки.
 */
public class BSTree<T extends Comparable<T>> implements Iterable<T> {
    /**
     * Представление узла дерева
     */
    private class Node {
        /**
         * Информационная нагрузка.
         */
        T info;

        // Ссылки на поддеревья и родительский узел.
        Node left, right, parent;

        /**
         * Конструктор листа. Предполагается, что ссылка на родительский узел появляется в тот момент,
         * когда созданный узел включается в структуру дерева.
         * @param info  Информационная часть нового листа.
         */
        Node(T info) { this.info = info; }

        /**
         * Конструктор узла. Предполагается, что ссылка на родительский узел появляется в тот момент,
         * когда созданный узел включается в структуру дерева. Созданный узел объявляется родительским
         * узлом для его непосредственных потомков (если они есть).
         * @param info   Информационная часть нового листа.
         * @param left   Левый потомок.
         * @param right  Правый потомок.
         */
        Node(T info, Node left, Node right) {
            this.info = info;
            this.left = left;
            this.right = right;
            if (left != null) left.parent = this;
            if (right != null) right.parent = this;
        }
    }

    /**
     * Корень дерева.
     */
    Node root;

    /**
     * Реализация итератора с проходом узлов слева направо (в порядке возрастания).
     * Реализация выполнена без использования вспомогательных структур данных и рекурсии.
     * Предполагается, что во время итерации структура дерева не меняется.
     */
    private class BSIterator implements Iterator<T> {
        /**
         * Указатель на очередной узел в итерации.
         */
        Node current;

        /**
         * Конструктор устанавливает в качестве текущего узла узел с минимальным значением.
         */
        BSIterator() {
            if (root != null) setFirst(root);
        }

        /**
         * Функция устанавливает в качестве текущего узла узел с наименьшим значением
         * в поддереве, определенном аргументом функции.
         * @param root   Корень поддерева, в котором происходит поиск наименьшего узла.
         */
        void setFirst(Node root) {
            while (root.left != null) root = root.left;
            current = root;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node result = current;

            // Поиск следующего узла в порядке итерации
            if (current.right != null) {
                // Правое поддерева непусто, следующим узлом будет наименьший узел в правом поддереве.
                setFirst(current.right);
            } else {
                // Ищем ближайшего предка, большего, чем текущий узел
                Node parent;
                while ((parent = current.parent) != null && parent.right == current) {
                    current = parent;
                }
                current = parent;
            }
            return result.info;
        }
    }

    /**
     * Реализация разделяющего итератора. Считаем, что число узлов в дереве подсчитывать слишком накладно,
     * поэтому итератор не предоставляет информации о размере итерируемого множества.
     */
    private class BSSpliterator implements Spliterator<T> {
        /**
         * Корень дерева с еще не просмотренными элементами.
         */
        Node root;

        /**
         * Очередной элемент для итерации (если есть)
         */
        Node current;

        /**
         * Конструктор устанавливает в качестве текущего узла узел с минимальным значением в дереве,
         * корень которого задан аргументом, а в качестве корня поддерева непросмотренных элементов - заданный аргумент.
         */
        BSSpliterator(Node root) {
            this.root = root;
            if (root != null) setFirst(root);
        }

        /**
         * Функция устанавливает в качестве текущего узла узел с наименьшим значением
         * в поддереве, определенном аргументом функции.
         * @param root   Корень поддерева, в котором происходит поиск наименьшего узла.
         */
        private void setFirst(Node root) {
            while (root.left != null) root = root.left;
            current = root;
        }

        /**
         * Сдвигает указатель текущего элемента на следующий по порядку в текущем поддере (если есть).
         */
        public void next() {
            if (current.right != null) {
                // Правое поддерево непусто, следующий элемент ищем в правом поддереве.
                setFirst(current.right);
            } else {
                // Правое поддерево пусто, следующий элемент ищем среди предков, это первый из тех,
                // который расположен справа от текущего.
                Node parent;
                while ((parent = current.parent) != null && parent.right == current) {
                    current = parent;
                }
                current = parent;
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            // Пытаемся найти текущий элемент и сдвинуть указатель на следующий.
            if (current == null) return false;
            action.accept(current.info);
            next();
            return true;
        }

        @Override
        public Spliterator<T> trySplit() {
            // Алгоритм пытается разделить узлы на узлы правого поддерева текущего дерева и все остальные.
            // При отсоединении правого поддерева корень текущего дерева пересоздается,
            // чтобы отсоединить правое поддерево.
            if (root == null || root.right == null) {
                // Правого поддерева нет, отсоединять нечего.
                return null;
            }
            // Корень правого поддерева пересоздается для того, чтобы отсоединить родительский узел.
            Node otherRoot = new Node(root.right.info, root.right.left, root.right.right);
            // Корень итерации создаетдля того, чтобы отделить правое поддерево.
            root = new Node(root.info, root.left, null);

            return new BSSpliterator(otherRoot);
        }

        @Override
        public long estimateSize() {
            // Оценка размера достаточно сложна, поэтому не производится.
            return Long.MAX_VALUE;
        }

        @Override
        public int characteristics() {
            // Считаем, что множество элементов:
            // - можно обрабатывать параллельно (CONCURRENT)
            // - среди элементов нет равных (DISTINCT)
            // - элементы упрядочены (ORDERED)
            // - элементы можно сортировать с помощью компаратора (SORTED)
            return CONCURRENT | DISTINCT | ORDERED | SORTED;
        }

        @Override
        public Comparator<T> getComparator() {
            // Сравнение элементов производится в "естественном" порядке.
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BSIterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return new BSSpliterator(root);
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public Stream<T> streamParallel() {
        return StreamSupport.stream(spliterator(), true);
    }

    //-------------------------------------------------------------------------
    // Вспомогательные функции для тестирования.
    //-------------------------------------------------------------------------

    /**
     * Создает поддерево из элементов упорядоченного массива.
     * @param array  Исходный массив.
     * @return       Корневой узел постренного поддерева.
     */
    private Node fromArray(T[] array) {
        return fromArray(array, 0, array.length);
    }

    /**
     * Вспомогательная рекурсивная функция для построения поддерева из фрагмента упорядоченного массива.
     * @param array  Исходный массив
     * @param from   Индекс первого элемента фрагмента
     * @param to     Индекс первого после фрагмента элемента
     * @return       Корневой узел постренного поддерева.
     */
    private Node fromArray(T[] array, int from, int to) {
        int len = to - from;
        switch (len) {
            case 0: return null;
            case 1: return new Node(array[from]);
            default:
                int mid = (from + to) / 2;
                Node left = fromArray(array, from, mid);
                Node right = fromArray(array, mid+1, to);
                return new Node(array[mid], left, right);
        }
    }

    /**
     * Строит дерево целых чисел из заданного диапазона.
     * @param start   Начальный элемент диапазона
     * @param finish  Последний элемент диапазона
     * @return        Построенное дерево
     */
    public static BSTree<Integer> buildTree(int start, int finish) {
        BSTree<Integer> tree = new BSTree<>();
        Integer[] numbers = Stream.iterate(start, n -> n+1).limit(finish - start + 1).toArray(Integer[]::new);
        tree.root = tree.fromArray(numbers);
        return tree;
    }

    /**
     * Тестирует итерацию элементов небольшого дерева.
     */
    private static void testIter() {
        BSTree<Integer> tree = buildTree(1, 20);

        for (Integer e : tree) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println("------------------------------");
    }

    /**
     * Потребитель, который печатает переданный ему элемент, делает перевод строки после каждого
     * @param <T>
     */
    private static class Printer<T> implements Consumer<T> {
        final int limit;
        int lineCounter = 0;
        int totalCounter = 0;

        Printer(int limit) {
            this.limit = limit;
        }

        Printer() {
            this(30);
        }
        
        public int getCounter() { return totalCounter; }

        @Override
        public void accept(T n) {
            if (lineCounter == limit) {
                System.out.println();
                lineCounter = 0;
            }
            System.out.print(n + " ");
            lineCounter++;
            totalCounter++;
        }
    }

    /**
     * Тестирует разделяющий итератор, создавая поток над элементами дерева,
     * который может обрабатываться как последовательно, так и параллельно.
     * @param parallel   Тип создаваемого потока (параллельный - true, последовательный - false).
     */
    private static void testSplit(boolean parallel) {
        BSTree<Integer> tree = buildTree(1, 300);
        Stream<Integer> stream = parallel ? tree.streamParallel() : tree.stream();
        Printer<Integer> printer = new Printer<>();
        stream.forEach(printer);
        System.out.println();
        System.out.println("--------- " + printer.getCounter() + " ------------");
    }

    /**
     * Функция, запускающая два простых теста.
     * @param args   Не используется.
     */
    public static void main(String[] args) {
        testIter();
        testSplit(false);
        testSplit(true);
    }
}
