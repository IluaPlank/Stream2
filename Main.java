import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(person -> 18 > person.getAge())
                .count();
        System.out.println(count);
        //количество людей не старше 18
        List<String> recruit = persons.stream()
                .filter(person -> 18 < person.getAge() && person.getAge() < 27)
                .filter(person -> Sex.MAN.equals(person.getSex()))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        //фамилии призывников
        List<Person> people = persons.stream()
                .filter(person -> Education.HIGHER.equals(person.getEducation()))
                .filter(person -> 18 < person.getAge())
                .filter(person -> (Sex.MAN.equals(person.getSex()) && person.getAge() < 65) ||
                        (Sex.WOMAN.equals(person.getSex()) && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
