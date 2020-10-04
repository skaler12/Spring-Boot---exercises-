package pl.poznanski.spring_boot_exercises;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.poznanski.spring_boot_exercises.mvc.ToDo;
import pl.poznanski.spring_boot_exercises.mvc.ToDoBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootExercisesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExercisesApplication.class, args);
      /*  //stworzenie instancji klasy to Do za pomocą wzorca - klasy todobuilder
        ToDoBuilder.create().withId("1").withDescription("fajnyObiekt").build();
        //to samo za pomoca konstruktora i settera
        ToDo toDo = new ToDo("fajnyobiekt");
        toDo.setId("1");
        */
      // w tym miejscu mozna testowac aplikacjie z adnotacja @ComandLineRunner
        //ksiazka Pro Spring Boot 2 - str 123.
    }
}
