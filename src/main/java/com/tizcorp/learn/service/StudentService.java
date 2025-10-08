package com.tizcorp.learn.service;

import com.tizcorp.learn.dto.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class StudentService {
    public void sortStudent() {
        ArrayList<Student> students = getStudents();
        Comparator<Student> studentComparator = Comparator.comparing(Student::getScore);
        students.sort(studentComparator);
        for (Student student : students) {
            System.out.println(student.getName());
        }
        System.out.println("students sorted by name");
    }

    public void countCharacter(){
        HashMap<Character, Integer> map = new HashMap<>();
        String chain = "abcdabefcc";
       for(int i = 0; i < chain.length(); i++){
           map.put(chain.charAt(i), map.getOrDefault(chain.charAt(i), 0) + 1);
       }
       System.out.println(map);
    }

    public void multithread () {
        Runnable task1 = () -> {
            for (int i = 0; i < 50; i++) {
                System.out.println("Task 1 - " + i);
            }
        };

        Runnable task2 = () -> {
            for (char c = 'A'; c <= 'Z'; c++) {
                System.out.println("Letter: " + c);
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("John");
        student1.setScore(8.0);
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Theme");
        student2.setScore(9.0);
        Student student3 = new Student();
        student3.setId(3);
        student3.setName("Tiny");
        student3.setScore(7.0);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        return students;
    }
}
