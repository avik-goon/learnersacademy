package main.webapp.pojo;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String subject_code;
    @Column
    private String subject_name;

    public Integer getId() {
        return id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subject_code='" + subject_code + '\'' +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}