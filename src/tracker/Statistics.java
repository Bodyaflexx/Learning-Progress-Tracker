package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Statistics {
    PrintInfo printInfo;
    String input;
    private StudentList studentList;
    Map<Integer, List<Integer>> listWithPoints;
    List<Courses> coursesList = new ArrayList<>();
    StringBuilder output = new StringBuilder();

    public void setListWithPoints(Map<Integer, List<Integer>> listWithPoints) {
        this.listWithPoints = listWithPoints;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public void printStatistic() {
        initCourses();
        System.out.println(Messages.statisticMessage);
        findStatistic();
        System.out.println(output);
        while (!input.equalsIgnoreCase("back")) {
            input = Input.readInput();
            if (input.equalsIgnoreCase("back")) {
                break;
            }
            switch (input.toUpperCase()) {
                case "JAVA" -> {
                    printInfo = new PrintInfo("Java", listWithPoints, 0);
                    printInfo.printInfo();
                }
                case "DSA" -> {
                    printInfo = new PrintInfo("DSA", listWithPoints, 1);
                    printInfo.printInfo();
                }
                case "DATABASES" -> {
                    printInfo = new PrintInfo("DataBases", listWithPoints, 2);
                    printInfo.printInfo();
                }
                case "SPRING" -> {
                    printInfo = new PrintInfo("Spring", listWithPoints, 3);
                    printInfo.printInfo();
                }
                default -> System.out.println("Unknown course.");
            }
        }
    }


    private void findStatistic() {
        coursesList.sort((o1, o2) -> o2.enrolledStudent() - o1.enrolledStudent());

        output.append("Most popular: ");
        coursesList.forEach(courses -> {
            if (courses.enrolledStudent() == coursesList.get(0).enrolledStudent()) {
                output.append(courses.courseName()).append(", ");
            }
        });
        output.append("\n");
        output.append("Least popular: ");
        String tmp = (coursesList.get(coursesList.size() - 1).enrolledStudent() < coursesList.get(0).enrolledStudent() ?
                coursesList.get(coursesList.size() - 1).courseName() : "n/a");
        output.append(tmp);
        output.append("\n");

        coursesList.sort((o1, o2) -> o2.activity() - o1.activity());

        output.append("Highest activity: ");
        coursesList.forEach(courses -> {
            if (courses.activity() == coursesList.get(0).activity()) {
                output.append(courses.courseName()).append(", ");
            }
        });
        output.append("\n");
        output.append("Lowest activity: ");
        tmp = (coursesList.get(coursesList.size() - 1).activity() < coursesList.get(0).activity() ?
                coursesList.get(coursesList.size() - 1).courseName() : "n/a");
        output.append(tmp);
        output.append("\n");

        coursesList.sort((o1, o2) -> o2.mediumScore() - o1.mediumScore());

        output.append("Easiest course: ").append(coursesList.get(0).courseName()).append("\n");
        output.append("Hardest course: ").append(coursesList.get(coursesList.size() - 1).courseName()).append("\n");
    }

    private void initCourses() {
        int javaStudents = findAmountOfStudents(0);
        int DSAStudents = findAmountOfStudents(1);
        int DatabasesStudents = findAmountOfStudents(2);
        int SpringStudents = findAmountOfStudents(3);
        Courses java;
        Courses DSA;
        Courses Databases;
        Courses Spring;
        if (javaStudents == 0 && DSAStudents == 0 && DatabasesStudents == 0 && SpringStudents == 0) {
            java = new Courses("n/a", 0, 0, 0);
            DSA = new Courses("n/a", 0, 0, 0);
            Databases = new Courses("n/a", 0, 0, 0);
            Spring = new Courses("n/a", 0, 0, 0);
        } else {
            java = new Courses("Java", javaStudents, studentList.getJavaActive(), studentList.getJavaScore());
            DSA = new Courses("DSA", DSAStudents, studentList.getDSAActive(), studentList.getDSAScore());
            Databases = new Courses("Databases", DatabasesStudents, studentList.getDatabasesActive(), studentList.getDatabasesScore());
            Spring = new Courses("Spring", SpringStudents, studentList.getSpringActive(), studentList.getSpringScore());
        }
        coursesList.add(java);
        coursesList.add(DSA);
        coursesList.add(Databases);
        coursesList.add(Spring);
    }

    private int findAmountOfStudents(int index) {
        int result = 0;
        for (var entry : listWithPoints.entrySet()) {
            List<Integer> tmp = entry.getValue();
            if (tmp.get(index) > 0) {
                result++;
            }
        }
        return result;
    }
}
