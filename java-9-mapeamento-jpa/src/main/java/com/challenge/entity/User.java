package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(User.class)
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Size(max = 100)
    @Column(name = "FULL_NAME")
    @NotNull
    private String fullName;

    @Size(max = 100)
    @NotNull
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "NICKNAME", length = 50)
    private String nickname;

    @NotNull
    @Column(name = "PASSWORD")
    @Size(max = 255)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidate> candidates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Submission> submissions;

    @Column(name = "CREATED_AT")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    @CreatedDate
    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
