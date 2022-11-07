import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignUpPageComponent } from './sign-up-page/sign-up-page.component';
import { LogInPageComponent } from './log-in-page/log-in-page.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { CompletedBooksComponent } from './completed-books/completed-books.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import {HttpClientModule} from '@angular/common/http';
import { GuestDashboardComponent } from './guest-dashboard/guest-dashboard.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpPageComponent,
    LogInPageComponent,
    WishlistComponent,
    CompletedBooksComponent,
    DashboardComponent,
    PageNotFoundComponent,
    GuestDashboardComponent,
    HeaderComponent
 
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
