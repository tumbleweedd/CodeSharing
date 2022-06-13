package com.example.codesharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "code_sharing")
public class Code {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private int time;
    private int views;
    @JsonIgnore
    private boolean timeRestricted;
    @JsonIgnore
    private boolean viewsRestricted;

    @Id
    @Column(name = "id")
    @JsonIgnore
    private String id;

    @Column(name = "name")
    private String code;

    @Column(name = "datee")
    private String date;

    public Code() {
    }

    public Code(String code, int time, int views, String id) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;

        this.time = Math.max(time, 0);
        this.views = Math.max(views, 0);

        this.id = id;

        this.timeRestricted = this.time > 0;
        this.viewsRestricted = this.views > 0;
    }
}
