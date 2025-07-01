import { Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { RecipesComponent } from './recipes/recipes.component';
import { MoviesComponent } from './movies/movies.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'recipes', component: RecipesComponent },
  { path: 'movies', component: MoviesComponent },
];
