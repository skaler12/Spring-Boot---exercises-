package pl.poznanski.spring_boot_exercises.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.poznanski.spring_boot_exercises.entity.Sth;

@Repository
public interface sthRepository extends CrudRepository<Sth,String> {
}
