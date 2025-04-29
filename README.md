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
GET    /api/recipes    Връща всички рецепти    -\
GET    /api/recipes/:id    Връща детайли за конкретна рецепта    -\
POST    /api/recipes    Създава нова рецепта    { title, description, ingredients, steps, movieId }\
PUT    /api/recipes/:id    Редактира съществуваща рецепта    { title?, description?, ingredients?, steps?, movieId? }\
DELETE    /api/recipes/:id    Изтрива рецепта    -\
GET    /api/movies    Връща всички филми    -\
GET    /api/movies/:id    Връща информация за конкретен филм    -\
POST    /api/movies    Добавя нов филм    { title, director, releaseDate, description }\
GET    /api/recipes/movie/:movieId    Връща всички рецепти от конкретен филм\
POST /api/auth/login логване на съществуващи потребители\
POST /api/auth/register Регистрация на нови потребители\
