package devel0per36.student;

import devel0per36.student.entity.Student;
import devel0per36.student.entity.component.Country;
import devel0per36.student.entity.component.FullName;
import devel0per36.student.exception.PropertyFileException;
import devel0per36.student.serialize.SerializationStudents;
import devel0per36.student.util.PrintingInfo;
import devel0per36.student.util.SortingStudents;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Класс для запуска приложения
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        String filenameProperty = "pathToSerializationObjects.properties";
        try {
            String line = PrintingInfo.DOTTED_LINE;
            SerializationStudents serializationStudents = new SerializationStudents(filenameProperty);
            List<Student> originalListStudents = createArrayStudents();
            System.out.println("Созданный список студентов:");
            outputStudents(originalListStudents);
            List<Student> sortedListStudents = sortStudents(originalListStudents);
            System.out.println("Отсортированный список студентов:");
            outputStudents(sortedListStudents);
            boolean serializationResult = serializationListStudents(serializationStudents, sortedListStudents);
            if (serializationResult) {
                System.out.println("Отсортированный список студентов сериализован");
            } else {
                System.out.println("Ошибка сериализации отсортированного списка студентов");
            }
            System.out.println(line);
            Optional<List<Student>> optionalListStudent = deserializationListStudents(serializationStudents);
            if (optionalListStudent.isPresent()) {
                System.out.println("Десериализованный отсортированный список студентов:");
                outputStudents(optionalListStudent.get());
            } else {
                System.out.println("Ошибка десериализации отсортированного списка студентов");
            }
        } catch (PropertyFileException | IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод для создания динамического массива студентов
     * @return - возвращает созданный список студентов
     */
    private static List<Student> createArrayStudents() {
        Student girlBelarus1 = new Student(66, new FullName("Краснова", "Дарья", "Васильевна"), 18, 9.3, Country.BELARUS);
        Student boyBelarus1 = new Student(78, new FullName("Иванов", "Максим", "Александрович"), 20, 8.5, Country.BELARUS);
        Student boyFrance1 = new Student(10, new FullName("Антонов", "Антон"), 18, 5.9, Country.FRANCE);
        Student girlJapan1 = new Student(23, new FullName("Нода", "Юи"), 23, 9.3, Country.JAPAN);
        Student girlJapan2 = new Student(35, new FullName("Сато", "Сакура"), 20, 6.1, Country.JAPAN);
        Student boyJapan1 = new Student(15, new FullName("Такахаси", "Рику"),21, 8.5, Country.JAPAN);
        Student boyJapan2 = new Student(48, new FullName("Ёсида", "Макото"), 18, 7.9, Country.JAPAN);
        Student boyKazakhstan1 = new Student(99, new FullName("Борисов", "Иван", "Васильевич"), 32, 8.1, Country.KAZAKHSTAN);
        Student girlRussia1 = new Student(78, new FullName("Огурцова", "Мария", "Егоровна"), 19, 8.1, Country.RUSSIA);
        Student girlRussia2 = new Student(83, new FullName("Соколова", "Татьяна", "Ивановна"), 20, 7.3, Country.RUSSIA);
        Student girlRussia3 = new Student(5, new FullName("Петрова", "Алиса", "Сергеевна"), 19, 9.0, Country.RUSSIA);
        Student boyRussia2 = new Student(1, new FullName("Николаев", "Альберт", "Олегович"), 18, 6.2, Country.RUSSIA);
        Student boyRussia1 = new Student(65, new FullName("Смирнов", "Андрей", "Васильевич"), 24, 9.9, Country.RUSSIA);
        Student boyRussia3 = new Student(53, new FullName("Микрофонов", "Роман", "Алексеевич"), 26, 9.0, Country.RUSSIA);
        Student boyRussia4 = new Student(97, new FullName("Конфетов", "Степан", "Иванович"), 18, 6.9, Country.RUSSIA);
        Student boyTurkey1 = new Student(1, new FullName("Корнеплодов", "Александр", "Александрович"), 21, 7.5, Country.TURKEY);
        return List.of(girlBelarus1, boyBelarus1, boyFrance1, girlJapan1, girlJapan2, boyJapan1, boyJapan2, boyKazakhstan1,
                girlRussia1, girlRussia2, girlRussia3, boyRussia1, boyRussia2, boyRussia3, boyRussia4, boyTurkey1);
    }

    /**
     * Метод для сортировки по имени тех студентов, у кого оценка выше 8 баллов (сначала происходит фильтрация)
     * @return возвращает отсортированный список
     */
    private static List<Student> sortStudents(List<Student> needSort) {
        return SortingStudents.moreThanRequestedRatingAndName(needSort, 8.0);
    }

    /**
     * Метод для сериализации списка студентов
     * @param serialization - объект, с помощью которого происходит сериализация
     * @param students - список студентов
     * @return - возвращает логический ответ о том, были ли сериализованы объекты
     */
    private static boolean serializationListStudents(SerializationStudents serialization, List<Student> students) {
        boolean flag = false;
        try {
            flag = serialization.serializationListStudents(students);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    /**
     * Метод для десериализации списка студентов
     * @param serialization - объект, с помощью которого происходит десериализация
     * @return возвращает класс-оболочку Optional со списком студентов
     */
    private static Optional<List<Student>> deserializationListStudents(SerializationStudents serialization) {
        Optional<List<Student>> optionalListStudent = null;
        try {
            optionalListStudent = serialization.deserializationListStudents();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return optionalListStudent;
    }

    /**
     * Метод для вывода списка студентов
     */
    private static void outputStudents(List<Student> students) {
        PrintingInfo.printDataListStudents(students);
    }
}
