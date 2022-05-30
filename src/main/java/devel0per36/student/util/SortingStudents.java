package devel0per36.student.util;

import devel0per36.student.entity.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для сортировки списка студентов
 * @version 1.0
 */
public class SortingStudents {
    /**
     * Метод для сортировки студентов по списку после фильтрации по оценке
     * @param students - список студентов
     * @param rating - оценка, по которой будет происходить фильтрация студентов
     * @return возвращает отсортированный список студентов после фильтрации по оценке
     */
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
