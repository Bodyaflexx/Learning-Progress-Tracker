package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PrintInfo {
    private final String nameOfCourse;
    private final Map<Integer, List<Integer>> listWithPoints;
    private final int index;
    private final Map<String, Integer> needPoints = new HashMap<>();

    public PrintInfo(String nameOfCourse, Map<Integer, List<Integer>> listWithPoints, int index) {
        this.nameOfCourse = nameOfCourse;
        this.listWithPoints = listWithPoints;
        this.index = index;
    }

    public void printInfo() {
        needPoints.put("Java", 600);
        needPoints.put("DSA", 400);
        needPoints.put("DataBases", 480);
        needPoints.put("Spring", 550);

        List<Integer> points = new ArrayList<>();
        listWithPoints.forEach((k, v) -> points.add(v.get(index)));
        points.sort((o1, o2) -> o2 - o1);

        System.out.println(nameOfCourse);
        System.out.println("id points completed");
        for (Integer point : points) {
            for (var entry : listWithPoints.entrySet()) {
                if (Objects.equals(entry.getValue().get(index), point)) {
                    if (entry.getValue().get(index) != 0) {
                        System.out.print(entry.getKey() + " ");
                        System.out.print(entry.getValue().get(index) + " ");
                        double result = (double) entry.getValue().get(index) / (double) needPoints.get(nameOfCourse) * 100.0;
                        BigDecimal tmp = new BigDecimal(result);
                        System.out.print(tmp.setScale(1, RoundingMode.HALF_UP) + "%\n");
                        break;
                    }
                }
            }
        }
    }
}
