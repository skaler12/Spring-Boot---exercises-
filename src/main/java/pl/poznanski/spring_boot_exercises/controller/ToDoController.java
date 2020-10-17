package pl.poznanski.spring_boot_exercises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.poznanski.spring_boot_exercises.entity.ToDo;
import pl.poznanski.spring_boot_exercises.repository.ToDoRepository;
import pl.poznanski.spring_boot_exercises.error.ToDoValidationError;
import pl.poznanski.spring_boot_exercises.error.ToDoValidationErrorBuilder;

import javax.validation.Valid;

//rest controller z wyswietleiem bledow oraz zwracajacy obiekt typu ResponseEntity
@RestController
@RequestMapping("/api")
public class ToDoController {
    private ToDoRepository repository;
    //zainicjalizaowanie repozytorium
    @Autowired
    public ToDoController(ToDoRepository repository) {
        this. repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/todo/{id}")
    public ResponseEntity<?> getToDoById(@PathVariable Integer id){
        return ResponseEntity.ok(repository.findById(id));
    }
    @PostMapping("/todo")
    public ResponseEntity<?> createUser(@Valid @RequestBody ToDo todo,
                                        Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDo result = repository.save(todo);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable Integer id){
        repository.delete(ToDo.builder().id(id).build());
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }
    //spersonalizowana obsluga bledow
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        return new ToDoValidationError(exception.getMessage());
    }
}
