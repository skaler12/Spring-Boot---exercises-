package pl.poznanski.spring_boot_exercises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.poznanski.spring_boot_exercises.entity.ToDo;

//implementcja interfejsu CommonRepository z typami generycznymi
@Repository
public interface ToDoRepository extends CrudRepository<ToDo,Integer> {
}