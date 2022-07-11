import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Генерирует файл со случайными данными в формате &lt;name, transaction sum&gt; и затем анализирует его
 * в поисках максимальной суммы транзакции человека с заданным именем.
 */
public class MaxTransaction {
    /**
     * Генерирует случайный набор данных и записывает его в файл.
     *
     * @param fileName  Имя файла
     * @throws FileNotFoundException  В случае, если файл с таким именем не может быть создан
     */
    public static void generateRandomData(String fileName) throws FileNotFoundException {
        Random rnd = new Random();
        String[] names = {"Smith", "Brown", "Doe", "Pupkin", "Linkoln", "Kern", "Kuchek"};
        PrintStream ps = new PrintStream(fileName);
        ps.println("Name\t\tSum\n");
        for (int i = 0; i < 10000; ++i) {
            ps.print(names[rnd.nextInt(names.length)]);
            ps.print("\t\t");
            ps.println(rnd.nextInt(200000)/100.0);
        }
        ps.close();
    }

    /**
     * Читает все данные в память и создает поток строк на основе прочитанных данных.
     *
     * @param fileName   Имя файла
     * @return           Поток строк из файла с указанным именем.
     * @throws FileNotFoundException    Если файл с указанным именем не найден.
     */
    private static Stream<String> getStreamFromScanner(String fileName) throws FileNotFoundException {
        // Reading data
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("\n");
        return scanner.tokens();
    }

    /**
     * Создает поток строк непосредственно из файла, используя новые средства Java8.
     * @param fileName   Имя файла
     * @return           Поток строк из файла с указанным именем.
     * @throws IOException    Если произошла ошибка при чтении данных
     */
    private static Stream<String> getStreamFromFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }

    /**
     * Используя операции над потоками, выбирает максимальную сумму транзакции у человека с указанным именем.
     *
     * @param stream   Заданный поток строк
     * @param name     Имя человека, по которому определяем транзакцию.
     * @return         Сумма максимальной транзакции или ноль в случае, если человек с этим именем не
     *                 совершал транзакций.
     */
    private static double getMaximum(Stream<String> stream, String name) {
        // Предикат, проверяющий, что заданная строка не пуста.
        Predicate<String> notEmpty = ((Predicate<String>) String::isEmpty).negate();

        // Находим максимальную сумму транзакции
        Optional<Double> d = stream
                .skip(1)                    // пропуск заголовка
                .map(String::trim)          // отрезаем пробелы в начале и в конце строк
                .filter(notEmpty.and(s -> !s.startsWith("//")))    // отфильтровываем пустые строки и комментарии
                .map(s -> s.split("\\s+"))  // делим строки на пары из имени и суммы
                .filter(s -> s[0].equalsIgnoreCase(name))          // выбираем строки с заданным именем клиента
                .map(s -> Double.parseDouble(s[1]))                // конвертируем в поток сумм
                .max(Comparator.naturalOrder());                   // ищем максимум

        return d.isPresent() ? d.get() : 0;
    }

    public static void main(String[] args) throws IOException {
        String fileName = args.length > 0 ? args[0] : "data.txt";
        String nameToAnalyze = args.length > 1 ? args[1] : "Pupkin";

        generateRandomData(fileName);
        long start;
        double maximum;

        start = System.currentTimeMillis();
        maximum = getMaximum(getStreamFromFile(fileName), nameToAnalyze);
        System.out.format("Elapsed millis from file: %d, found result: %g\n",
                System.currentTimeMillis() - start, maximum);

        start = System.currentTimeMillis();
        maximum = getMaximum(getStreamFromFile(fileName), nameToAnalyze);
        System.out.format("Elapsed millis from file: %d, found result: %g\n",
                System.currentTimeMillis() - start, maximum);

        start = System.currentTimeMillis();
        maximum = getMaximum(getStreamFromScanner(fileName), nameToAnalyze);
        System.out.format("Elapsed millis from scanner: %d, found result: %g\n",
                System.currentTimeMillis() - start, maximum);
    }
}
