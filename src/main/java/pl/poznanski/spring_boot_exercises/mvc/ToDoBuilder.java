package pl.poznanski.spring_boot_exercises.mvc;
//klasa builder - wzorzec builder
public class ToDoBuilder {
    private static ToDoBuilder instance = new ToDoBuilder();
    private String id = null;
    private String description = "";
    //prywatny konstruktor tak by nikt nie stworzyl instancji tej klasy
    private ToDoBuilder(){

    }
    //metoda zwracajaca instancje klasy ToDoBuilder
    public static ToDoBuilder create() {
        return instance;
    }
    //nadanie opisu
    public ToDoBuilder withDescription(String description){
        this.description = description;
        return instance;
    }
    //nadanie id
    public ToDoBuilder withId(String id){
        this.id = id;
        return instance;
    }
    //budowanie obiektu ToDo
    public ToDo build(){
        ToDo result = new ToDo(this.description);
        if(id != null)
            result.setId(id);
        return result;
    }
}