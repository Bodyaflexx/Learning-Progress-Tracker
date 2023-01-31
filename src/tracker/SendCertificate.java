package tracker;

import java.util.*;

public class SendCertificate {
    private Map<Integer, String> listOfStudents;
    private Map<Integer, List<Integer>> listOfStudentsWithPoints;
    private final Map<String, Integer> needPoints = new HashMap<>();
    private final List<Integer> competeID = new ArrayList<>();
    private final Map<Integer, List<String>> result = new HashMap<>();

    public void setListOfStudents(Map<Integer, String> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void setListOfStudentsWithPoints(Map<Integer, List<Integer>> listOfStudentsWithPoints) {
        this.listOfStudentsWithPoints = listOfStudentsWithPoints;
    }


    public SendCertificate() {
        needPoints.put("Java", 600);
        needPoints.put("DSA", 400);
        needPoints.put("DataBases", 480);
        needPoints.put("Spring", 550);
    }

    public void send() {
        findStudents();
        clearIdsMap();
        int count = result.size();
        result.forEach((k, v) -> {
            listOfStudents.forEach((id, name) -> {
                if (Objects.equals(k, id)) {
                    for (String s : v) {
                        System.out.printf("To: %s\n" +
                                        "Re: Your Learning Progress\n" +
                                        "Hello, %s! You have accomplished our %s course!\n"
                                , name.split(" ")[2]
                                , name.split(" ")[0] + " " + name.split(" ")[1], s);
                    }
                }
            });
            competeID.add(k);
        });
        System.out.printf("Total %d students have been notified.\n", count);

    }

    private void findStudents() {
        listOfStudentsWithPoints.forEach((k, v) -> {
            List<String> tmp = new ArrayList<>();
            for (Integer integer : v) {
                needPoints.forEach((name, points) -> {
                    if (Objects.equals(integer, points)) {
                        tmp.add(name);
                    }
                });
            }
            if (!tmp.isEmpty()) {
                result.put(k, tmp);
            }
        });
    }

    private void clearIdsMap() {
        try {
            result.forEach((id, courses) -> {
                if (competeID.contains(id)) {
                    result.remove(id);
                }
            });
        } catch (Exception ignored) {

        }
    }

}
