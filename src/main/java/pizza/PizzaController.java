package pizza;

import java.time.LocalTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaController {
    
    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    Logger logger = LoggerFactory.getLogger(PizzaController.class);

    @PostMapping("/pizzas")
    public @ResponseBody Pizza addNewUser (@RequestBody Pizza pizza) {

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        pizza.setId(randomUUIDString);

        //print the pizza name
        logger.info("Pizza name: " + pizza.getName());

        LocalTime time = LocalTime.now();
        pizza.setDateAdded(time.toString());

        pizzaRepository.save(pizza);
        return pizza;
    }

    @GetMapping("/pizzas")
    public @ResponseBody Iterable<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    //Find pizza by id
    @GetMapping("/pizzas/{id}")
    public @ResponseBody Pizza getPizzaById(@PathVariable String id) {
        return pizzaRepository.findById(id).get();
    }

    //Fetch all pizzas by category
    @GetMapping("/pizzas/category/{category}")
    public @ResponseBody Iterable<Pizza> getPizzasByCategory(@PathVariable String category) {
        return pizzaRepository.findAllByCategory(category);
    }

    //Fetch all pizzas by search term
    @GetMapping("/pizzas/search/{searchTerm}")
    public @ResponseBody Iterable<Pizza> getPizzasBySearchTerm(@PathVariable String searchTerm) {
        return pizzaRepository.findAllByNameContaining(searchTerm);
    }

    //Delete pizza by id
    @PostMapping("/pizzas/delete/{id}")
    public @ResponseBody String deletePizzaById(@PathVariable String id) {
        pizzaRepository.deleteById(id);
        return "Pizza deleted";
    }

    //Update pizza by id keep date added the same
    @PostMapping("/pizzas/update/{id}")
    public @ResponseBody Pizza updatePizzaById(@PathVariable String id, @RequestBody Pizza pizza) {
        Pizza pizzaToUpdate = pizzaRepository.findById(id).get();
        pizzaToUpdate.setName(pizza.getName());
        pizzaToUpdate.setDescription(pizza.getDescription());
        pizzaToUpdate.setPrice(pizza.getPrice());
        pizzaToUpdate.setRating(pizza.getRating());
        pizzaToUpdate.setPicture(pizza.getPicture());
        pizzaToUpdate.setCategory(pizza.getCategory());
        pizzaToUpdate.setIngredients(pizza.getIngredients());
        pizzaRepository.save(pizzaToUpdate);
        return pizzaToUpdate;
    }

}
