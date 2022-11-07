import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SignUpPageComponent } from "./sign-up-page/sign-up-page.component";
import { LogInPageComponent } from "./log-in-page/log-in-page.component";
import { WishlistComponent } from "./wishlist/wishlist.component";
import { CompletedBooksComponent } from "./completed-books/completed-books.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { GuestDashboardComponent } from "./guest-dashboard/guest-dashboard.component";

const routes: Routes = [
  { path: "", redirectTo: "signup", pathMatch: "full" },
  { path: "login", component: LogInPageComponent },
  { path: "signup", component: SignUpPageComponent },
  { path: "dashboard", component: DashboardComponent },
  { path: "guest-dashboard", component: GuestDashboardComponent },
  { path: "wishlist", component: WishlistComponent },
  { path: "completedBooks", component: CompletedBooksComponent },
  {
    path: "not-found",
    component: PageNotFoundComponent,
    data: { message: "Page Not Found-404" },
  },
  { path: "**", redirectTo: "not-found" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
