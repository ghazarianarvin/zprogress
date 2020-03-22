package com.zprogress.domain.objects;

import javax.persistence.*;

@Entity
@Table(name = "Goal")
public class GoalEntity extends AbstractGoal {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}
