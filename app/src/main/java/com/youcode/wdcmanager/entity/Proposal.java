package com.youcode.wdcmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
public class Proposal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String subject;
    private String content;
    @ManyToOne
    private User user;
    @ManyToOne
    private Deal deal;

}
