package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
@Repository
public class StudentRepository {
    private HashMap<String,Student> studentDb = new HashMap<>();
    private HashMap<String,Teacher> teacherDb = new HashMap<>();

    private HashMap<String,List<String>> teacherStudentDb = new HashMap<>();

    public void addStudent(Student student){
        String name = student.getName();
        studentDb.put(name,student);
    }
    public void addTeacher(Teacher teacher){
        String name = teacher.getName();
        teacherDb.put(name,teacher);
    }

    public Student getStudentByName(String name){
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }
    public void addStudentTeacherPair(String student, String teacher){
        List<String> list = new ArrayList<>();
        if(teacherStudentDb.containsKey(teacher)){
            list =teacherStudentDb.get(teacher);
            list.add(student);
        }
        teacherStudentDb.put(teacher,list);
        teacherDb.get(teacher).setNumberOfStudents(list.size());

    }
    public List<String> getStudentsByTeacherName(String teacher){
        return teacherStudentDb.get(teacher);

    }
    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for(String student:studentDb.keySet()){
            list.add(student);
        }
        return list;
    }
    public void deleteTeacherByName(String name){
        List<String> students = new ArrayList<>();
        if(teacherStudentDb.containsKey(name)){
            students=teacherStudentDb.get(name);
            for(String student: students){
                studentDb.remove(student);
            }
            teacherStudentDb.remove(name);
        }
    }
    public void deleteAllTeachers(){
        for (List<String> student_list: teacherStudentDb.values()){
            for (String s: student_list){
                studentDb.remove(s);
            }
        }
        teacherDb.clear();
        teacherDb.clear();
    }

}
