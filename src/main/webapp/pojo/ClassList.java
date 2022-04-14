package main.webapp.pojo;

import javax.persistence.*;

@Entity
@Table(name = "class_master")
public class ClassList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    private String class_name;
    @Column
    private String section;

    @Column
    private String standard;

    public String getDate() {
        return date;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column
    private String date;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "ClassList{" +
                "id=" + id +
                ", class_name='" + class_name + '\'' +
                ", section='" + section + '\'' +
                ", standard='" + standard + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}