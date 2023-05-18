package com.dgomezt.practicaintegradora.entities.embeddables;

import com.dgomezt.practicaintegradora.entities.User;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Auditory {
    @Column(name = "entry_date")
    private LocalDate entryDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "entry_user_id")
    private UserAdmin entryUser;

    @Column(name = "last_modification_date")
    private LocalDate lastModificationDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "last_modification_user_id")
    private UserAdmin lastModificationUser;

    @Column(name = "removed_date")
    private LocalDate removedDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "removed_user_id")
    private UserAdmin removedUser;
}