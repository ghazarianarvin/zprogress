package com.zprogress.reporsitory;

import com.zprogress.AbstractGoal;
import com.zprogress.entity.GoalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CrudRepository<GoalEntity, Long> {

}
