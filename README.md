# MovieBites

## E/R Diagram
![MovieBites-ER](https://github.com/user-attachments/assets/e168888f-0e10-4c0a-b25e-e48e9774591b)
## Relational model (Tables with their attributes)
Users(<ins>id</ins>, username, name, email, password)\
Recipes(<ins>id</ins>, name, summary, description, dificulty, user_id, category_id, movie_id)\
Recipe_Categories(<ins>id</ins>, name)\
Ingredients(<ins>id</ins>, name)\
Movies(<ins>id</ins>, title, year, director, summary, photo, genre_id)\
Movie_genres(<ins>id</ins>, name)\
Contains(<ins>recipe_id</ins>, <ins>ingredient_id</ins>)
## REST API Documentation

