package com.zprogress.domain.reporsitories;

import com.zprogress.domain.objects.GoalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends CrudRepository<GoalEntity, Long> {
}
