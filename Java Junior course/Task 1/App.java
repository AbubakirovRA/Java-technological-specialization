import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<Integer> numbers = new Random().ints(1, 1_000_000 + 1)
                                            .limit(1_000)
                                            .boxed()
                                            .collect(Collectors.toList());

        System.out.println(numbers);

        int maxNumber = numbers.stream()
                        .max(Integer::compareTo)
                        .orElseThrow(null); // Если список пуст, выбросить исключение
        System.out.println("Maximum number: " + maxNumber);

        int sum = numbers.stream()
                 .filter(num -> num > 500_000)
                 .mapToInt(num -> (num * 5) - 150)
                 .sum();

        System.out.println("Amount after conversions: " + sum);

        long count = numbers.stream()
                    .filter(num -> num * num < 100_000)
                    .count();

        System.out.println("Number of numbers whose square is less than 100_000: " + count);
    }
}
