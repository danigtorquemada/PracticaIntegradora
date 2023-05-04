package com.dgomezt.practicaintegradora.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "last_modification_date")
    private LocalDate lastModificationDate;

    @Column(name = "removed_date")
    private LocalDate removedDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "entry_user_id")
    private User entryUser;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "last_modification_user_id")
    private User lastModificationUser;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "removed_user_id")
    private User removedUser;
}