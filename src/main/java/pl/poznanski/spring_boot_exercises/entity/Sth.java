package pl.poznanski.spring_boot_exercises.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @Builder
    public class Sth {

        @NotNull
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        private String id;
        @NotNull
        @NotBlank
        private String description;
        @Column(insertable = true, updatable = false)
        private LocalDateTime created;
        private LocalDateTime modified;
        private boolean completed;
        public Sth(String description){
            this.description = description;
        }

        public Sth(String id, @NotBlank String description, LocalDateTime created, LocalDateTime modified, boolean completed) {
            this.id = id;
            this.description = description;
            this.created = created;
            this.modified = modified;
            this.completed = completed;
        }

        @PrePersist
        void onCreate() {
            this.setCreated(LocalDateTime.now());
            this.setModified(LocalDateTime.now());
        }
        @PreUpdate
        void onUpdate() {
            this.setModified(LocalDateTime.now());
        }
    }