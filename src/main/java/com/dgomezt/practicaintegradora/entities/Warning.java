package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.helpers.WarningLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "warning")
public class Warning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "warning_level_id", foreignKey = @ForeignKey(name = "FK_warning_warningLevel"))
    private WarningLevel warningLevel;

    @Column(name = "processing_date")
    private LocalDate processingDate;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_warning_user"))
    private User user;

    @Column(name = "product_stock")
    private Integer productStock;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_warning_product"))
    private Product product;
}