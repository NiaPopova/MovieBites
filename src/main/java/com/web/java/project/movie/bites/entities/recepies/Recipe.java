package com.web.java.project.movie.bites.entities.recepies;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class Recipe {

    private int id;
    private String name;
    private String description;
    private String summary;
    private String dificulty;

    @ManyToMany
    @JoinTable(
        name = "contains",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    public Recipe(int id, String name, String description, String summary, String dificulty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.dificulty = dificulty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", summary=" + summary + ", dificulty=" + dificulty + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Recipe other = (Recipe) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (dificulty == null) {
            if (other.dificulty != null)
                return false;
        } else if (!dificulty.equals(other.dificulty))
            return false;
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result + ((dificulty == null) ? 0 : dificulty.hashCode());
        return result;
    }

    public static Recipe createRecipe(int id, String name, String description, String summary, String dificulty) {
        return new Recipe(id, name, description, summary, dificulty);
    }

}