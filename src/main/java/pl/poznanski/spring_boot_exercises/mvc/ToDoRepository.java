package pl.poznanski.spring_boot_exercises.mvc;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//implementcja interfejsu CommonRepository z typami generycznymi
@Repository
public class ToDoRepository implements CommonRepository<ToDo> {
    //zainicjalizowanie Maoy obiektó To Do.
    private Map<String,ToDo> toDos = new HashMap<>();

    //zwykly zapis obiektu klasy .to do. do mapy.
    @Override
    public ToDo save(ToDo domain) {
        ToDo result = toDos.get(domain.getId());
        if(result != null) {
            result.setModified(LocalDateTime.now());
            result.setDescription(domain.getDescription());
            result.setCompleted(domain.isCompleted());
            domain = result;
        }
        toDos.put(domain.getId(), domain);
        return toDos.get(domain.getId());
    }
    //zapisanie całej kolekcji do mapy
    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        //this::save powoduje przekazanie kazdego elementy kolekcji do metody save
        domains.forEach(this::save);
        return findAll();
    }
    //usuniecie elementu z mapy
    @Override
    public void delete(ToDo domain) {
        toDos.remove(domain.getId());
    }
    //wyszukanie elementu z mapy
    @Override
    public ToDo findById(String id) {
        return toDos.get(id);
    }
    //zwrócenie całej mapy - wszystkich elementów
    @Override
    public Iterable<ToDo> findAll() {
        return toDos.entrySet().stream().sorted(entryComparator).
                map(Map.Entry::getValue).collect(Collectors.toList());
    }
    private Comparator<Map.Entry<String,ToDo>> entryComparator = (Map.
                                                                          Entry<String, ToDo> o1, Map.Entry<String, ToDo> o2) -> {
        return o1.getValue().getCreated().compareTo(o2.getValue().
                getCreated());
    };
}
