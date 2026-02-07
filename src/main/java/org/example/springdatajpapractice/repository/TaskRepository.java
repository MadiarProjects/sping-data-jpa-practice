package org.example.springdatajpapractice.repository;
import org.example.springdatajpapractice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedToId(int assignedToId);
    //  select * from tasks where assigned_to = ?

    List<Task> findByAssignedToIdIn(List<Integer> assignedTo);
    //  select * from tasks where assigned_to in (1,2,3)

    List<Task> findByStatus(Task.Status status);

    List<Task> findByStatusIn(List<Task.Status> statuses);

    List<Task> findByAssignedToIdInAndStatusIn(List<Integer> assignedTo, List<Task.Status> statuses);

    @Query("""
            select t from Task t
            where (:assignedTo is null or t.assignedTo.id in (:assignedTo))
              and (:statuses is null or t.status in (:statuses))
              and (:priority is null or t.priority in (:priority))
            """)
    List<Task> findByMultipleCondition(List<Integer> assignedTo,
                                       List<Task.Status> statuses,
                                       List<Task.Priority> priority);
}
