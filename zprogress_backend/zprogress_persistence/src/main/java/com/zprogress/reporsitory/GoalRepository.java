package com.zprogress.reporsitorie;

import com.zprogress.AbstractGoal;
import com.zprogress.entity.GoalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CrudRepository<GoalEntity, Long> {

    default void save(AbstractGoal goal) {
//        this.save()
    }
}
