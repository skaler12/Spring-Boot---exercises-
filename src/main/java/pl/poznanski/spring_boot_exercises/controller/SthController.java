package pl.poznanski.spring_boot_exercises.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.poznanski.spring_boot_exercises.entity.Sth;
import pl.poznanski.spring_boot_exercises.repository.sthRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
    @RequestMapping("/app/data")
    public class SthController {
    private sthRepository sthRepository;

    @Autowired
    public SthController(sthRepository sthRepository) {
        this.sthRepository = sthRepository;
    }

    @GetMapping("/sth")
    public ResponseEntity<Iterable<Sth>> getSth() {
        return ResponseEntity.ok(sthRepository.findAll());
    }

    @GetMapping("/sth/{id}")
    public ResponseEntity<Sth> getSthById(@PathVariable String id) {
        Optional<Sth> sth = sthRepository.findById(id);
        if (sth.isPresent())
            return ResponseEntity.ok(sth.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sth/{id}")
    public ResponseEntity<Sth> setCompleted(@PathVariable String id) {
        Optional<Sth> sth = sthRepository.findById(id);
        if (!sth.isPresent())
            return ResponseEntity.notFound().build();
        Sth result = sth.get();
        result.setCompleted(true);
        sthRepository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).
                build();
    }
    @DeleteMapping("/sth/{id}")
    public ResponseEntity<Sth> deleteToDo(@PathVariable String id){
        sthRepository.delete(Sth.builder().id(id).build());
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/todo")
    public ResponseEntity<Sth> deleteToDo(@RequestBody Sth sth){
        sthRepository.delete(sth);
        return ResponseEntity.noContent().build();
    }
}

