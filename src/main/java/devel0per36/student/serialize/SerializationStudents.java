package devel0per36.student.serialize;

import devel0per36.student.entity.Student;
import devel0per36.student.exception.PropertyFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SerializationStudents {
    private String directory;
    private String filename;

    public SerializationStudents(String filenameProperty) throws PropertyFileException, IOException {
        init(filenameProperty);
    }

    public boolean serializationListStudents(List<Student> students) throws IOException {
        if (students.isEmpty()) {
            return false;
        }
        if (directory.isBlank() || filename.isBlank()) {
            return false;
        }
        Path pathDirectory = Paths.get(directory);
        if (!Files.exists(pathDirectory)) {
            Files.createDirectory(pathDirectory);
        }
        String pathToFile = directory + File.separator + filename;
        writeObjectToFile(pathToFile, students);
        return true;
    }

    public Optional<List<Student>> deserializationListStudents() throws IOException {
        String pathToFile = directory + File.separator + filename;
        Path pathFile = Paths.get(pathToFile);
        if (!Files.exists(pathFile)) {
            throw new IOException("File for read not found");
        }
        Optional<List<Student>> optionalDeserializedStudents = readObjectFromFile(pathToFile);
        return optionalDeserializedStudents;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFilename() {
        return filename;
    }

    private void init(String filenameProperty) throws PropertyFileException, IOException {
        if ((filenameProperty == null) || filenameProperty.isBlank()) {
            throw new PropertyFileException("File property not found");
        }
        final Properties properties = new Properties();
        ClassLoader loader = SerializationStudents.class.getClassLoader();
        InputStream inputStream = loader.getResourceAsStream(filenameProperty);
        if (inputStream == null) {
            throw new PropertyFileException("File property not found");
        }
        properties.load(inputStream);
        String tempDirectory = properties.getProperty("directory");
        String tempFileName = properties.getProperty("filename");
        if (!checkContentPropertyFile(tempDirectory, tempFileName)) {
            throw new PropertyFileException("File content is empty");
        }

        this.directory = tempDirectory;
        this.filename = tempFileName;
    }

    private boolean checkContentPropertyFile(String directory, String filename) {
        if ((directory == null) || (filename == null)) {
            return false;
        }
        return true;
    }

    private void writeObjectToFile(String pathToFile, List<Student> students) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(pathToFile)
        )) {
            outputStream.writeObject(students);
        } catch (IOException ex) {
            System.err.println("Ошибка сериализации списка студентов: " + ex.getMessage());
        }
    }

    private Optional<List<Student>> readObjectFromFile(String pathToFile) {
        List<Student> deserializedList = new ArrayList<>();
        try(ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(pathToFile)
        )) {
            deserializedList = (List<Student>) inputStream.readObject();
        } catch (IOException ex) {
            System.err.println("Ошибка десериализация списка студентов: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Не найден соответствующий класс для десериализации: " + ex.getMessage());
        }
        if (deserializedList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(deserializedList);
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(",", SerializationStudents.class.getSimpleName() + "{", "}")
                .add("directory=" + directory).add("filename=" + filename).toString();
    }
}
