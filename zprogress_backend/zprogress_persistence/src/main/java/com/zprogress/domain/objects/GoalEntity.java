package com.zprogress.domain.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Goal")
public class GoalEntity extends AbstractGoal {

    @Id
    private Long id;
}
