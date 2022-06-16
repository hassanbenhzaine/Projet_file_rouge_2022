package com.youcode.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.crm.enums.MODE_OF_TRANSPORT_CODE;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "TYPES_OF_TRANSPORT")
public class TransportType {

    @Id
    @Enumerated(EnumType.STRING)
    private MODE_OF_TRANSPORT_CODE code;

    private String fullName;
    private Double minLength;
    private Double maxLength;
    private Double minWeight;
    private Double maxWeight;
    private Integer transportCapacity;


    @OneToMany(mappedBy = "modeOfTransportCode", cascade = {REMOVE, MERGE})
    @JsonIgnore
    private Set<Supplier> suppliers = new HashSet<>();

}
