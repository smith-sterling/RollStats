import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> advRolls = new TreeMap<>();
        Map<Integer, Integer> disRolls = new TreeMap<>();
        Map<Integer, Integer> superAdvRolls = new TreeMap<>();
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                advRolls.merge(max(i, j) + 1, 1, Integer::sum);
                disRolls.merge(min(i, j) + 1, 1, Integer::sum);
                for (int k = 0; k < 20; ++k) {
                    superAdvRolls.merge(max(max(i, j), k), 1, Integer::sum);
                }
            }
        }
//        advRolls.forEach((key, value) -> System.out.println(key + ": " + value));
//        disRolls.forEach((key, value) -> System.out.println(key + ": " + value));
//        superAdvRolls.forEach((key, value) -> System.out.println(key + ": " + value));

        List<Integer> advRollsArray = new ArrayList<>();
        advRolls.forEach((key, value) -> { for (int i = 0; i < value; ++ i) { advRollsArray.add(key); } });
        System.out.println("\nAdvantage:");
        printMean(advRollsArray);
        printMedian(advRollsArray);

        List<Integer> disRollsArray = new ArrayList<>();
        disRolls.forEach((key, value) -> { for (int i = 0; i < value; ++ i) { disRollsArray.add(key); } });
        System.out.println("\nDisadvantage:");
        printMean(disRollsArray);
        printMedian(disRollsArray);

        List<Integer> superAdvArray = new ArrayList<>();
        superAdvRolls.forEach((key, value) -> { for (int i = 0; i < value; ++ i) { superAdvArray.add(key); } });
        System.out.println("\nSuper Advantage:");
        printMean(superAdvArray);
        printMedian(superAdvArray);
    }
    public static void printMean(List<Integer> values) {
        System.out.println("The mean is " +
                (double)values.stream().mapToInt(value -> value).sum() / values.size());
        //or, alternatively,
//        System.out.println("The mean is " +
//                values.stream().collect(Collectors.averagingDouble(Integer::doubleValue)));
    }
    public static void printMedian(List<Integer> values) {
        System.out.println("The median is " +
                values.stream()
                        .sorted()
                        .collect(Collectors.collectingAndThen(
                                Collectors.toList(), list -> { return list.size() % 2 == 0 ?
                                            (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0 :
                                            list.get(list.size() / 2);
                                })));
//        values.sort(Integer::compareTo);
//        System.out.println("The median is " + values.get(values.size() / 2));
    }
}