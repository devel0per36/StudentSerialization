package devel0per36.student.entity.component;

import java.util.StringJoiner;

/**
 * Перечисление для выбора Страны
 * @version 1.0
 */
public enum Country {
    RUSSIA("643", "Россия"), BELARUS("112", "Беларусь"),
    KAZAKHSTAN("398", "Казахстан"), CHINA("156", "Китай"),
    SERBIA("688", "Сербия"), TURKEY("792", "Турция"),
    FRANCE("250", "Франция"), JAPAN("392", "Япония");

    /* Код */
    private String code;
    /* Наименование */
    private String name;

    /**
     * Конструктор для перечисления типа Country
     * @param code - код
     * @param name - наименование
     */
    Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", Country.class.getSimpleName() + "{", "}")
                .add("code=" + code).add("name=" + name).toString();
    }
}
