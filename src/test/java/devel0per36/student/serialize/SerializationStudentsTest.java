package devel0per36.student.serialize;

import devel0per36.student.entity.Student;
import devel0per36.student.entity.component.Country;
import devel0per36.student.entity.component.FullName;
import devel0per36.student.exception.PropertyFileException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class SerializationStudentsTest {
    private SerializationStudents serialization;
    private List<Student> students;

    @BeforeAll
    public static void initAll() {
        try {
            String directory = getValueFromPropertyFile("otherPathToSerializationTestObjects.properties", "directory");
            Files.createDirectory(Paths.get(directory));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @BeforeEach
    public void init() {
        Student girlJapan1 = new Student(23, new FullName("Нода", "Юи"), 23, 9.3, Country.JAPAN);
        Student girlJapan2 = new Student(35, new FullName("Сато", "Сакура"), 20, 6.1, Country.JAPAN);
        Student boyJapan1 = new Student(15, new FullName("Такахаси", "Рику"),21, 8.5, Country.JAPAN);
        Student boyJapan2 = new Student(48, new FullName("Ёсида", "Макото"), 18, 7.9, Country.JAPAN);
        Student girlRussia1 = new Student(78, new FullName("Огурцова", "Мария", "Егоровна"), 19, 8.0, Country.RUSSIA);
        Student girlRussia2 = new Student(83, new FullName("Соколова", "Татьяна", "Ивановна"), 20, 7.3, Country.RUSSIA);
        Student boyRussia2 = new Student(1, new FullName("Николаев", "Альберт", "Олегович"), 18, 6.2, Country.RUSSIA);
        Student boyRussia1 = new Student(65, new FullName("Смирнов", "Андрей", "Васильевич"), 24, 9.9, Country.RUSSIA);
        students = List.of(girlRussia1, girlRussia2, girlJapan1, girlJapan2, boyRussia1, boyRussia2, boyJapan1, boyJapan2);
    }

    @Test
    @DisplayName("Попытка инициализировать объект типа SerializationStudents с помощью пустой строки")
    public void tryInitObjectWithBlankString() {
        Assertions.assertThrows(PropertyFileException.class, () -> new SerializationStudents(""),
                "File property not found");
    }

    @Test
    @DisplayName("Попытка инициализировать объект типа SerializationStudents с помощью null")
    public void tryInitObjectWithNull() {
        Assertions.assertThrows(PropertyFileException.class, () -> new SerializationStudents(null),
                "File property not found");
    }

    @Test
    @DisplayName("Попытка инициализировать объект типа SerializationStudents несуществующим property-файлом")
    public void tryInitObjectNotExistsFile() {
        Assertions.assertThrows(PropertyFileException.class, () -> new SerializationStudents("random.properties"),
                "File property not found");
    }

    @Test
    @DisplayName("Попытка инициализировать объект типа SerializationStudents property-файлом с пустым содержимым")
    public void tryInitObjectPropertyFileWithEmptyContent() {
        Assertions.assertThrows(PropertyFileException.class, () -> new SerializationStudents("empty.properties"),
                "File content is empty");
    }

    @Test
    @DisplayName("Сериализация списка студентов в несуществующую директорию")
    public void serializationAndDeserializationListStudentsToNotExistsDirectory() {
        try {
            serialization = new SerializationStudents("pathToSerializationTestObjects.properties");
            Assertions.assertEquals(8, students.size());
            serialization.serializationListStudents(students);

            String directory = serialization.getDirectory();
            String filename = serialization.getFilename();
            Path path = Paths.get(directory + File.separator + filename);
            Assertions.assertTrue(Files.exists(path));

            Optional<List<Student>> optionalListStudent = serialization.deserializationListStudents();
            Assertions.assertTrue(optionalListStudent.isPresent());
            List<Student> tempListStudent = optionalListStudent.get();
            Assertions.assertEquals(8, tempListStudent.size());

            Path pathDirectory = Paths.get(directory);
            Files.delete(path);
            Files.delete(pathDirectory);
            Assertions.assertFalse(Files.exists(path));
            Assertions.assertFalse(Files.exists(pathDirectory));
        } catch (PropertyFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Сериализация списка студентов в существующую директорию")
    public void serializationAndDeserializationListStudentsToExistsDirectory() {
        try {
            serialization = new SerializationStudents("otherPathToSerializationTestObjects.properties");
            Assertions.assertEquals(8, students.size());
            serialization.serializationListStudents(students);

            String directory = serialization.getDirectory();
            String filename = serialization.getFilename();
            Path path = Paths.get(directory + File.separator + filename);
            Assertions.assertTrue(Files.exists(path));

            Optional<List<Student>> optionalListStudent = serialization.deserializationListStudents();
            Assertions.assertTrue(optionalListStudent.isPresent());
            List<Student> tempListStudent = optionalListStudent.get();
            Assertions.assertEquals(8, tempListStudent.size());

            Files.delete(path);
            Assertions.assertFalse(Files.exists(path));
        } catch (PropertyFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void completeAll() {
        try {
            String directory = getValueFromPropertyFile("otherPathToSerializationTestObjects.properties", "directory");
            Files.delete(Paths.get(directory));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getValueFromPropertyFile(String filenameProperty, String key) throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = SerializationStudentsTest.class.getClassLoader();
        InputStream is = loader.getResourceAsStream(filenameProperty);
        properties.load(is);
        String value = properties.getProperty(key);
        return value;
    }
}
