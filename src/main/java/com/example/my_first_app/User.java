package com.example.my_first_app;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity          // Macht die Klasse zu einer Datenbanktabelle
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement

    private Integer id;
    // damit User nur ein Konto mit gelich email stellen
    // und darf not Null sein
    @Column(unique = true,nullable = false) // Pflichtfeld + darf sich nicht wiederholen
    private String email;
    @Column(nullable = false)  // Pflichtfeld
    private String password;

    @OneToMany   // Ein Benutzer hat mehrere Todos
    @JoinColumn(name ="userId")  // Fremdschlüssel in Todo-Tabelle
    private Set<Todo> todos; // Hibernate JBA unterstützt Set besser für OneToMany


    private String secret;  // So heißt die Spalte in der DB





    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public String getSecret() {
        return secret;
    }


    @JsonIgnore  // Nicht im JSON anzeigen So schützen wir API-Key vor der Ausgabe im JSON.
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
