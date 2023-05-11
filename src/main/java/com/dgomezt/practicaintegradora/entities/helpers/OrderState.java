package com.dgomezt.practicaintegradora.entities.helpers;

import com.dgomezt.practicaintegradora.entities.embeddables.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_state",
        uniqueConstraints = @UniqueConstraint(name = "UK_orderState_abbreviation", columnNames = {"abbreviation"}))
public class OrderState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    private Type type;
}