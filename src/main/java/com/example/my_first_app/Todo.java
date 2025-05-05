package com.example.my_first_app;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity     // Wird zu einer Tabelle
public class Todo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // Autoincrement

    private Integer id;
    private String description;
    private boolean isDone;

    private Integer userId;   // Verknüpfung zum User

    public Integer getId() {return this.id;}

    public void setId(Integer id) {this.id = id;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}

    public boolean getIsDone(){return this.isDone;}

    public void setIsDone(boolean isDone) {this.isDone = isDone;}

    public Integer getUserId() {return userId;}

    public void setUserId(Integer userId) {this.userId = userId;}
}
