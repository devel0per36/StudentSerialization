package devel0per36.student.entity;

import devel0per36.student.entity.component.Country;
import devel0per36.student.entity.component.FullName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Класс для описания Студента
 * @version 1.0
 */
public class Student implements Cloneable, Serializable {
    /* Идентификатор */
    private long id;
    /* Имя */
    private FullName name;
    /* Возраст */
    private int age;
    /* Оценка */
    private double rating;
    /* Страна */
    private Country country;
    /* Код UID для сериализации объекта */
    private static final long serialVersionUID = 2L;

    /**
     * Конструктор для создания нового объекта типа Student
     * @param id - идентификатор
     * @param name - имя
     * @param age - возраст
     * @param rating - оценка
     * @param country - страна
     */
    public Student(long id, FullName name, int age, double rating, Country country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rating = rating;
        this.country = country;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public FullName getName() {
        return name;
    }
    public void setName(FullName name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.name = name.clone();
        return student;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Student student = (Student) obj;
        return (id == student.id) && (name == student.name || ((name != null) && name.equals(student.name))) &&
                (age == student.age) && (rating == student.rating) && (country == student.country);
    }

    @Override
    public int hashCode() {
        final int CODE = (int)(31 * 1 + id + (name == null ? 0 : name.hashCode()) + age + rating +
                (country == null ? 0 : country.hashCode()));
        return CODE;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", Student.class.getSimpleName() + "{", "}")
                .add("id=" + id).add("name=" + name).add("age=" + age)
                .add("rating=" + rating).add("country=" + country).toString();
    }
}
