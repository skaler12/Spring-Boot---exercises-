package pl.poznanski.spring_boot_exercises.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    public ToDo() {

    }
    public ToDo(Integer id, String description){
        //potrzebne by odwolac sie do konstuktora bezparametrowego
        this.id=id;
        this.description = description;
    }
}