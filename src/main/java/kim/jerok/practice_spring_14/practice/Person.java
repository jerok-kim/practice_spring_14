package kim.jerok.practice_spring_14.practice;

public class Person {
    private int id;
    private String name;

    public static Person builder() {
        return new Person();
    }

    public Person id(int id) {
        this.id = id;
        return this;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public static void main(String[] args) {
        Person p = Person.builder().id(1).name("jerok");
    }
}
