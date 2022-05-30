package devel0per36.student.util;

import devel0per36.student.entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingStudents {
    public static List<Student> moreThanRequestedRatingAndName(List<Student> students, double rating) {
        Comparator<Student> comparator = Comparator.comparingDouble(Student::getRating)
                .thenComparing((st1, st2) -> st1.getName().getLastName().compareTo(st2.getName().getLastName()));
        List<Student> result = students.stream()
                .filter(student -> student.getRating() > rating)
                .sorted(comparator)
                .collect(Collectors.toList());
        return result;
    }
}
