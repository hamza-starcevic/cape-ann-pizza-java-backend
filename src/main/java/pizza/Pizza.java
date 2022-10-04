package pizza;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
class Pizza {
    
    private @Id String id;
    private String name;
    private String description;
    private Float price;
    private Float rating;
    private String picture;
    private String category;
    private String ingredients;
    private @DateTimeFormat String date_added;

    public Pizza() {}

    public Pizza(
        String id,
        String name,
        String description,
        Float price,
        Float rating,
        String picture,
        String category,
        String ingredients,
        String date_added
           ) {     
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.picture = picture;
        this.category = category;
        this.ingredients = ingredients;
        this.date_added = date_added;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Float getPrice() {
        return this.price;
    }

    public Float getRating() {
        return this.rating;
    }

    public String getPicture() {
        return this.picture;
    }

    public String getCategory() {
        return this.category;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public String getDateAdded() {
        return this.date_added;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setPicture(String image) {
        this.picture = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIngredients(String Ingredients) {
        this.ingredients = Ingredients;
    }

    public void setDateAdded(String dateAdded) {
        this.date_added = dateAdded;
    }
}
