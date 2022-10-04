package pizza;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface PizzaRepository extends JpaRepository<Pizza, String> {

    public List<Pizza> findAllByCategory(String category);

    public Iterable<Pizza> findAllByNameContaining(String searchTerm);

}
    
