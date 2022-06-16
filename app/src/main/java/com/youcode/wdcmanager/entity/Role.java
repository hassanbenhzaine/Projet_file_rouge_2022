package com.youcode.wdcmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Short id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL) @JsonIgnore
    private List<User> userList = new ArrayList<>();
}

