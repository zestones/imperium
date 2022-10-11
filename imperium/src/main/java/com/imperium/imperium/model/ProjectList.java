package com.imperium.imperium.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "ProjectList")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectList {

    //This class representes the List where user can add tasks, it can be "Upcoming", "In progress", "Done", ...etc. 
    
    @Id
    @GeneratedValue
    private Long id;

    //title which is "Upcoming","Done",...etc.
    String title;

    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_project", referencedColumnName = "id")
    private Project project;

}

