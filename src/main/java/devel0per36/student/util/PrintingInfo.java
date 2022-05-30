package devel0per36.student.util;

import devel0per36.student.entity.Student;

import java.util.List;

public class PrintingInfo {
    /* Пунктирная линия */
    public final static String DOTTED_LINE = "---------------------------------------------------";

    public static void printDataStudent(Student student) {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: " + student.getId())
                .append(String.format("\nИмя: %s %s %s", student.getName().getLastName(), student.getName().getFirstName(),
                        (student.getName().getMiddleName() == null ? "" : student.getName().getMiddleName())))
                .append("\nВозраст: " + student.getAge() + " лет/год(-а)")
                .append("\nОценка: " + student.getRating())
                .append("\nСтрана: " + student.getCountry().getName());
        String data = builder.toString();
        System.out.println(String.format("%s%n%s", data, DOTTED_LINE));
    }

    public static void printDataListStudents(List<Student> students) {
        System.out.println(DOTTED_LINE);
        students.forEach(PrintingInfo::printDataStudent);
    }
}
