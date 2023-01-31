package tracker;

import java.util.*;
import java.util.stream.Collectors;

public class StudentList {
    private final Map<Integer, String> listOfStudents = new LinkedHashMap<>();
    private final Map<Integer, List<Integer>> listOfStudentsWithPoints = new LinkedHashMap<>();
    private int id = 0;
    private Checker checker;
    private String input;
    private int javaActive;
    private int DSAActive;
    private int DatabasesActive;
    private int SpringActive;
    private int javaScore;
    private int DSAScore;
    private int DatabasesScore;
    private int SpringScore;

    public Map<Integer, String> getListOfStudents() {
        return listOfStudents;
    }

    public Map<Integer, List<Integer>> getListOfStudentsWithPoints() {
        return listOfStudentsWithPoints;
    }

    public int getJavaScore() {
        return javaScore / javaActive;
    }

    public int getDSAScore() {
        return DSAScore / DSAActive;
    }

    public int getDatabasesScore() {
        return DatabasesScore / DatabasesActive;
    }

    public int getSpringScore() {
        return SpringScore / SpringActive;
    }

    public int getJavaActive() {
        return javaActive;
    }

    public int getDSAActive() {
        return DSAActive;
    }

    public int getDatabasesActive() {
        return DatabasesActive;
    }

    public int getSpringActive() {
        return SpringActive;
    }

    private void addStudent() {
        listOfStudents.put(id++, input);
    }


    public void setInput(String input) {
        this.input = input;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public void addStudents() {
        int counter = 0;
        System.out.println(Messages.enterStudentMessage);
        while (!input.equalsIgnoreCase("back")) {
            input = Input.readInput();
            checker.setText(input);
            checker.setList(listOfStudents);
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            if (checker.mainChecker()) {
                counter++;
                addStudent();
                System.out.println(Messages.addStudentMessage);
            }
        }
        Messages.printTotal(counter);
    }

    public void showStudents() {
        if (listOfStudents.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            listOfStudents.forEach((id, name) -> System.out.println(id));
        }
    }

    public void addPoints() {
        System.out.println(Messages.addPointMessage);
        while (!input.equalsIgnoreCase("back")) {
            input = Input.readInput();
            checker.setId(input.split(" ")[0]);
            checker.setText(input);
            checker.setListWithPoints(listOfStudentsWithPoints);
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            if (checker.mainCheckerForPoints()) {
                addPoint();
            }
        }
    }

    private void addPoint() {
        int tmpId = Integer.parseInt(input.split(" ")[0]);
        List<Integer> marks = new ArrayList<>();
        marks.add(Integer.parseInt(input.split(" ")[1]));
        marks.add(Integer.parseInt(input.split(" ")[2]));
        marks.add(Integer.parseInt(input.split(" ")[3]));
        marks.add(Integer.parseInt(input.split(" ")[4]));
        boolean up = false;
        for (var entry : listOfStudentsWithPoints.entrySet()) {
            if (entry.getKey() == tmpId) {
                for (int i = 0; i < marks.size(); i++) {
                    marks.set(i, marks.get(i) + entry.getValue().get(i));
                }
                entry.setValue(marks);
                up = true;
            }
        }
        if (!up) {
            listOfStudentsWithPoints.put(tmpId, marks);
        }
        if (marks.get(0) > 0) {
            javaActive++;
            javaScore += marks.get(0);
        }
        if (marks.get(1) > 0) {
            DSAActive++;
            DSAScore += marks.get(1);
        }
        if (marks.get(2) > 0) {
            DatabasesActive++;
            DatabasesScore += marks.get(2);
        }
        if (marks.get(3) > 0) {
            SpringActive++;
            SpringScore += marks.get(3);
        }
        System.out.println("Points updated.");
    }

    public void findStudents() {
        System.out.println(Messages.findStudentMessage);
        while (!input.equalsIgnoreCase("back")) {
            input = Input.readInput();
            checker.setId(input.split(" ")[0]);
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            if (checker.checkStudent()) {
                findStudent();
            }
        }
    }

    public void findStudent() {
        List<Integer> marks = listOfStudentsWithPoints.get(Integer.parseInt(input));
        System.out.printf("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n"
                , input, marks.get(0), marks.get(1), marks.get(2), marks.get(3));
    }
}
