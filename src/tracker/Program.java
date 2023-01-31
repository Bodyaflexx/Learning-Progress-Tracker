package tracker;


public class Program {
    String command;
    StudentList studentList = new StudentList();
    Checker checker = new Checker();
    Statistics statistics = new Statistics();
    SendCertificate sendCertificate = new SendCertificate();

    public void startProgram() {
        System.out.println(Messages.greeting);
        commandHandler();
    }


    private void commandHandler() {
        command = Input.readInput();
        command = checker.checkCommand(command);
        studentList.setChecker(checker);
        studentList.setInput(command);
        statistics.setInput(command);
        while (true) {
            switch (command) {
                case "" -> System.out.println(Messages.emptyInput);
                case "exit" -> {
                    System.out.println(Messages.byeMessage);
                    return;
                }
                case "add students" -> studentList.addStudents();
                case "back" -> System.out.println(Messages.exitMessage);
                case "list" -> studentList.showStudents();
                case "add points" -> studentList.addPoints();
                case "find" -> studentList.findStudents();
                case "statistics" -> {
                    statistics.setStudentList(studentList);
                    statistics.setListWithPoints(studentList.getListOfStudentsWithPoints());
                    statistics.printStatistic();
                }
                case "notify" -> {
                    sendCertificate.setListOfStudents(studentList.getListOfStudents());
                    sendCertificate.setListOfStudentsWithPoints(studentList.getListOfStudentsWithPoints());
                    sendCertificate.send();
                }
                default -> System.out.println(Messages.error);
            }
            command = Input.readInput();
            studentList.setInput(command);
        }
    }


}
