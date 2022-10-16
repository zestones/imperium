package com.imperium.imperium.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
/* Access Table  */
public class Access {

    private Boolean canEdit;
    private Boolean canRead;

    @Id
    @GeneratedValue
    private Long id;
/* Foreign Key into Table User */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private User user;
/* Foreign Key into Table Project */
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_project", referencedColumnName = "id", nullable = false)
    private Project projects;

/* methods set user Right, can read and can write */
    public void setAccess(Boolean read) {
        setCanEdit(!read);
        setCanRead(true);
    }
}
