# MovieBites

MovieBites е уеб платформа, която позволява на потребителите да споделят и откриват рецепти, вдъхновени от култови филмови ястия. Независимо дали става дума за прочутия Ratatouille, Butterbeer от Хари Потър или десерт в стила на Уес Андерсън — тук кулинарията среща киното.

---

### Основни функционалности

- Регистрация и вход в системата  
  Потребителите могат да създават профили и да влизат, за да получат достъп до пълната функционалност.

- Споделяне на рецепти  
  Регистрирани потребители могат да добавят нови рецепти, включващи:
  - Кратко описание
  - Подробни инструкции за приготвяне
  - Ниво на трудност

- Асоцииране с филм  
  Всяка рецепта е свързана с конкретен филм с кратко описание.

- Списък с продукти  
  Рецептите съдържат съставки чрез релация `Contains`.

- Категории и жанрове  
  Рецептите се групират по категории, а филмите — по жанрове.

---

## E/R Диаграма

![MovieBites-ER](https://github.com/user-attachments/assets/e168888f-0e10-4c0a-b25e-e48e9774591b)

---

## Релационен модел (таблици с атрибути)
Users (<ins>id</ins>, username, name, email, password)\
Recipes (<ins>id</ins>, name, summary, description, dificulty, user_id, category_id, movie_id)\
Recipe_Categories (<ins>id</ins>, name)\
Ingredients (<ins>id</ins>, name)\
Movies (<ins>id</ins>, title, year, director, summary, photo, genre_id)\
Movie_genres (<ins>id</ins>, name)\
Contains (<ins>recipe_id</ins>, <ins>ingredient_id</ins>)

---

## Документация на REST API

- `GET    /movie/bites/recipes`                   Връща всички рецепти
- `GET    /movie/bites/recipes/:id`               Връща рецепта по ID
- `POST   /movie/bites/recipes`                   Създава нова рецепта    { title, description, ingredients, steps, movieId }
- `PUT    /movie/bites/recipes/:id`               Редактира съществуваща рецепта    { title?, description?, ingredients?, steps?, movieId? }
- `DELETE /movie/bites/recipes/:id`               Изтрива рецепта
- `GET    /movie/bites/recipes/movie/:movieId`    Връща всички рецепти от конкретен филм

- `GET    /movie/bites/movies`                    Връща всички филми
- `GET    /movie/bites/movies/:id`                Връща информация за конкретен филм
- `POST   /movie/bites/movies`                    Добавя нов филм    { title, director, releaseDate, description }

- `POST   /movie/bites/login`                     Логване на съществуващи потребители
- `POST   /movie/bites/register`                  Регистрация на нови потребители    { username, email, password, name }

## Допълнителна функционалност

- Удостоверяване чрез JWT токени
- DTO и mapper слой за отделяне на моделите
- Централизирана обработка на грешки с @RestControllerAdvice
- Конфигурация чрез Spring Boot & Security
- Свързаност с DB2 база данни чрез Hibernate/JPA

---

## Идеи за бъдещо развитие

| Функционалност | Описание |
|----------------|----------|
| Контрол на достъп по роли | @PreAuthorize("hasRole('ADMIN')") за администратори |
| Swagger/OpenAPI | Генерирана документация в браузъра |
| Покритие с тестове | Unit и интеграционни тестове |
| Оценяване и любими | Потребителите да гласуват и запазват рецепти |
| Търсачка по съставка или жанр | По-усъвършенствано филтриране |

---


Проектът е разработен като бекенд приложение с REST архитектура, обединяващо страстта към киното и кулинарията. Frontend приложението е в процес на развитие.
