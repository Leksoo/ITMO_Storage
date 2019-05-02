package ru.ifmo.rain.feponov.student;


import info.kgeorgiy.java.advanced.student.Group;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentGroupQuery;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StudentDB implements StudentGroupQuery {

    private Comparator<Student> nameComparator = Comparator.comparing(Student::getLastName, String::compareTo)
            .thenComparing(Student::getFirstName, String::compareTo)
            .thenComparingInt(Student::getId);

    //------------------------------------------------------------------------------------------------------


    private List<Group> getGroups(Supplier<TreeSet<Student>> supplier, Collection<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, TreeMap::new
                        , Collectors.toCollection(supplier)))
                .entrySet().stream()
                .map(el -> new Group(el.getKey(), new ArrayList<>(el.getValue())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Group> getGroupsByName(Collection<Student> students) {
        return getGroups(() -> new TreeSet<>(nameComparator), students);
    }

    @Override
    public List<Group> getGroupsById(Collection<Student> students) {
        return getGroups(TreeSet::new, students);
    }

    private <A, R> String getLargest(Collector<Student, A, R> collector,
                                     ToLongFunction<Entry<String, R>> longFunction, Collection<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, collector))
                .entrySet().stream()
                .max(Comparator.comparingLong(longFunction)
                        .thenComparing(Entry::getKey, Collections.reverseOrder(String::compareTo)))
                .map(Entry::getKey).orElse("");
    }

    @Override
    public String getLargestGroup(Collection<Student> students) {
        return getLargest(Collectors.counting(), Entry::getValue, students);
    }


    @Override
    public String getLargestGroupFirstName(Collection<Student> students) {
        return getLargest(Collectors.toList(), group -> getDistinctFirstNames(group.getValue()).size(), students);


    }

    //-------------------------------------------------------------------------------------------------

    private List<String> getStudentProperties(List<Student> students, Function<Student, String> fun) {
        return students.stream().map(fun).collect(Collectors.toList());
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return getStudentProperties(students, Student::getFirstName);
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return getStudentProperties(students, Student::getLastName);
    }

    @Override
    public List<String> getGroups(List<Student> students) {
        return getStudentProperties(students, Student::getGroup);
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return getStudentProperties(students,
                student -> student.getFirstName() + " " + student.getLastName());
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return students.stream().map(Student::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public String getMinStudentFirstName(List<Student> students) {
        return students.stream().min(Student::compareTo).map(Student::getFirstName).orElse("");
    }

    //--------------------------------------------------------------------------------------------

    private List<Student> sortBy(Collection<Student> students, Comparator<Student> cmp) {
        return students.stream().sorted(cmp).collect(Collectors.toList());
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return sortBy(students, Student::compareTo);
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return sortBy(students, nameComparator);
    }

    //----------------------------------------------------------------------------------------------------

    private List<Student> filterAndSortByProperty(
            Collection<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .sorted(nameComparator)
                .collect(Collectors.toList());
    }

    private Predicate<Student> getPredicate(String property, Function<Student, String> fun) {
        return student -> property.equals(fun.apply(student));
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return filterAndSortByProperty(students, getPredicate(name, Student::getFirstName));
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return filterAndSortByProperty(students, getPredicate(name, Student::getLastName));
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, String group) {
        return filterAndSortByProperty(students, getPredicate(group, Student::getGroup));
    }

    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, String group) {
        return students.stream()
                .filter(getPredicate(group, Student::getGroup))
                .collect(Collectors.toMap(
                        Student::getLastName,
                        Student::getFirstName,
                        BinaryOperator.minBy(String::compareTo)));
    }
}
