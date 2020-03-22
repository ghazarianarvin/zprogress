package com.zprogress.entity;

import com.zprogress.AbstractGoal;

import javax.persistence.*;

@Entity
@Table(name = "Goal")
public class GoalEntity extends AbstractGoal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
