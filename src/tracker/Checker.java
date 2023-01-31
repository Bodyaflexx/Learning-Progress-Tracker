package tracker;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    String text;
    Pattern patternForFirstName = Pattern.compile("^[^-']+[a-zA-Z'-]*[^-']+$");
    Pattern stupidPattern = Pattern.compile("-'|'-|--|''");
    Pattern patternForLastName = Pattern.compile("^[^-']+['-?a-zA-Z\\s]*[^-']+$");
    Pattern patternForEmail = Pattern.compile("[a-zA-Z0-9.]+@\\w+\\.\\w+");
    Pattern notLatin = Pattern.compile("[^a-zA-Z-'\\s+]");
    Matcher matcher;
    String[] splitText;
    String firstName;
    String lastname;
    String email;
    Map<Integer, String> list;
    Map<Integer, List<Integer>> listWithPoints;
    String id;

    public void setText(String text) {
        this.text = text;
        splitText = text.trim().split(" ");
        firstName = splitText[0].trim();
        email = splitText[splitText.length - 1].trim();
        lastname = makeLastName().trim();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setListWithPoints(Map<Integer, List<Integer>> listWithPoints) {
        this.listWithPoints = listWithPoints;
    }

    public void setList(Map<Integer, String> list) {
        this.list = list;
    }

    private String makeLastName() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < splitText.length - 1; i++) {
            result.append(splitText[i]).append(" ");
        }
        return result.toString();
    }

    public boolean mainChecker() {
        if (!checkAllText()) {
            System.out.println("Incorrect credentials");
            return false;
        }
        if (!checkerFirstName()) {
            System.out.println("Incorrect first name");
            return false;
        }
        if (!checkerLastName()) {
            System.out.println("Incorrect last name");
            return false;
        }
        if (!checkerEmail()) {
            System.out.println("Incorrect email");
            return false;
        }
        if (!checkOnHas()) {
            System.out.println("This email is already taken.");
            return false;
        }
        return true;
    }

    public boolean checkerFirstName() {
        matcher = patternForFirstName.matcher(firstName);
        if (!matcher.matches()) {
            return false;
        }
        matcher = stupidPattern.matcher(firstName);
        if (matcher.find()) {
            return false;
        }
        matcher = notLatin.matcher(firstName);
        return !matcher.find();
    }

    public boolean checkerLastName() {
        matcher = patternForLastName.matcher(lastname);
        if (!matcher.matches()) {
            return false;
        }
        matcher = stupidPattern.matcher(lastname);
        if (matcher.find()) {
            return false;
        }
        matcher = notLatin.matcher(lastname);
        return !matcher.find();
    }

    public boolean checkerEmail() {
        matcher = patternForEmail.matcher(email);
        return matcher.matches();
    }

    private boolean checkAllText() {
        return !firstName.isEmpty() && !lastname.isEmpty() && !email.isEmpty();
    }

    public String checkCommand(String command) {
        if (command.matches("\\s+") || command.isEmpty()) {
            return "";
        }
        return command;
    }

    public boolean checkOnHas() {
        for (var entry : list.entrySet()) {
            String tmp = entry.getValue().split(" ")[2];
            if (tmp.equals(email)) {
                return false;
            }
        }
        return true;
    }

    public boolean mainCheckerForPoints() {
        if (!checkOnHasInListWithPoints()) {
            System.out.printf("No student is found for id=%s\n", id);
            return false;
        }
        if (!checkPointFormat()) {
            System.out.println("Incorrect points format");
            return false;
        }
        return true;
    }

    private boolean checkOnHasInListWithPoints() {
        if (!id.matches("\\d+")) {
            return false;
        }
        return list.containsKey(Integer.parseInt(id));
    }

    public boolean checkStudent() {
        if (!listWithPoints.containsKey(Integer.parseInt(id))) {
            System.out.printf("No student is found for id=%s\n", id);
            return false;
        }
        return true;
    }

    private boolean checkPointFormat() {
        String[] tmp = text.split(" ");
        if (tmp.length != 5) {
            return false;
        }
        for (int i = 1; i < tmp.length; i++) {
            if (!tmp[i].matches("\\d+")) {
                return false;
            }
            if (Integer.parseInt(tmp[i]) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCourseOnHAs() {
        String[] courses = {"Java", "DSA", "Databases", "Spring"};
        for (String s : courses) {
            if (Objects.equals(s, text)) {
                return true;
            }
        }
        System.out.println("Unknown course");
        return false;
    }

}
