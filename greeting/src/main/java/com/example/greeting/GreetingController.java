package com.example.greeting;

import com.example.greeting.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.parseInt;

@RestController
@CrossOrigin(origins  =  "http://localhost:3000")
public class GreetingController {

    private ArrayList<Greeting> greetings = new ArrayList<>();

    // dependency injection
        // avoids us needing to make a new instance
    @Autowired
    GreetingRepository repository;


    @GetMapping("/greeting/{id}")
    public ResponseEntity<Optional<Greeting>> getGreetingById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
        //what made up my response
            //status code
            //body -our actual greeting
            //headers - additional info re the request and response
        //return repository.findById(id);<-come from CRUD is function findByID (passes STRING)

        //ResponseEntity
       // return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));

//        return greetings.stream()
//                .filter(greeting -> greeting.getId() == parseInt(id))
//                .findFirst()
//                .orElse(null);
    }


    //GET a specific greeting
//    @GetMapping("/welcome/{id}")
//    public ResponseEntity<String> getGreetingById(@PathVariable int id){
//
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(repository.findByid(id).toString());
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id: " + id +" Doesn't exist");
//        }


    @GetMapping("/greetings")
    public ResponseEntity<List<Greeting>> getAllGreetings() {
 //       return greetings;
//        repository spring methods with data access (get and save)
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }





    @GetMapping("/random")
    public ResponseEntity<Greeting> getRandomGreeting() {
        Random r = new Random();
        List<Greeting> allGreetings = getAllGreetings().getBody();
        Greeting randomGreeting = allGreetings.get(r.nextInt(allGreetings.size()));
        return ResponseEntity.status(HttpStatus.OK).body(randomGreeting);

    }
      //Works with array
       // @GetMapping("/random")
        //public Greeting getRandomGreeting(){
            //Random r = new Random();
            //Greeting randomGreeting = greetings.get(r.nextInt(greetings.size()));
           // return randomGreeting;}

       // refactor to get random greeting from database, not greetings array
            // .count to find number of entries in repository / .findById with repository.count() as argument
            // int index = 1 + r.nextInt((int) (repository.count()));
            // return repository.findByid(index);
        // .findAll (already written ^^) to get all of the existing greetings

    @PostMapping("/greetings")
    public ResponseEntity<String> createGreeting(@RequestBody Greeting greeting) {
    //public String createGreeting(@RequestBody Greeting greeting) {
                 // set the greetings id based on the greetings list, set the created by
        //greeting.setId(greetings.size() + 1);
        //greeting.setCreatedBy("Ollie");
        //greeting.setDateCreated(new Timestamp(System.currentTimeMillis()));

        //repository.save(greeting);
        //return "Greeting added";

        repository.save(greeting);
        return ResponseEntity.status(HttpStatus.OK).body("Greeting added");
    }

    // UPDATE route
    @PutMapping("/greetings/{id}")
    public String updateFullGreeting(@PathVariable int id, @RequestBody Greeting newGreeting){
        Greeting updatedGreeting = greetings.get(id);
        updatedGreeting.setGreeting(newGreeting.getGreeting());
        updatedGreeting.setCreatedBy(newGreeting.getCreatedBy());
        updatedGreeting.setOriginCountry(newGreeting.getOriginCountry());
        return "Greeting with id: " + id + "changed to" + newGreeting;
    }

    // DELETE route
    @DeleteMapping("/greetings/{id}")
    @Transactional //not needed for ArrayList
    public ResponseEntity<String> deleteGreeting(@PathVariable int id) {
//        greetings.remove(greetings.get(id));
//        return "Greeting with id: " + id + " deleted.";
        repository.delete(repository.findByid(id));
  //      return "Greeting with id: " + id + " deleted";
        return ResponseEntity.status(HttpStatus.OK).body("Greeting added");
    }
}



