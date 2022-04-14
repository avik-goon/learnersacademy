package main.webapp.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String student_roll;

    @Column
    private String student_name;
    @Column
    private String student_age;


    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_list_id")
    private ClassList classList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_roll() {
        return student_roll;
    }

    public void setStudent_roll(String student_roll) {
        this.student_roll = student_roll;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_age() {
        return student_age;
    }

    public void setStudent_age(String student_age) {
        this.student_age = student_age;
    }

    public ClassList getClassList() {
        return classList;
    }

    public void setClassList(ClassList classList) {
        this.classList = classList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", student_roll='" + student_roll + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_age='" + student_age + '\'' +
                ", classList=" + classList +
                '}';
    }
}