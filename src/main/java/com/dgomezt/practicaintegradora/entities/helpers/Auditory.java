package com.dgomezt.practicaintegradora.entities.helpers;

import com.dgomezt.practicaintegradora.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "auditory")
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "entry_user_id",
            foreignKey = @ForeignKey(name = "FK_auditory_entryUser"))
    private User entryUser;

    @Column(name = "last_modification_date")
    private LocalDate lastModificationDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "last_modification_user_id",
            foreignKey = @ForeignKey(name = "FK_auditory_lastModificationUser"))
    private User lastModificationUser;

    @Column(name = "removed_date")
    private LocalDate removedDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "removed_user_id",
            foreignKey = @ForeignKey(name = "FK_auditory_removedUser"))
    private User removedUser;
}