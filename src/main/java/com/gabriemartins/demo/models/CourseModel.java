package com.gabriemartins.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = CourseModel.TABLE_NAME)
public class CourseModel {
    public static final String TABLE_NAME = "course";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId", unique = true)
    private Long courseId;

    @Column(name = "courseName", length = 50, nullable = true)
    @NotBlank
    private String courseName;

    @OneToMany(mappedBy = "userCourse")
    @JsonBackReference //Essa anotação é de suma importância pois ela não permite a inserção de um loop infinito na inserção de usuários. Sem ela, ocorrerá um loop infinito quando tentarmos adicionar um usuário  
    private List<UserModel> users = new ArrayList<UserModel>();


    public CourseModel() {}


    public CourseModel(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }


    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    //Método para averiguar igualdade de dois objetos
    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        }

        if(!(obj instanceof UserModel)){
            return false;
        }

        CourseModel course = (CourseModel) obj;
        return Objects.equals(course.courseId, courseId) && Objects.equals(course.courseName, courseName);
    }

    public List<UserModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    //Método para gerar um código unico de identificação de um objeto
    @Override
    public int hashCode(){
        return Objects.hash(
            courseId,
            courseName
        );
    }
}
