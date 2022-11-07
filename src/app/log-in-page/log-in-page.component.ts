import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators,FormBuilder, FormGroup ,FormControl} from "@angular/forms";
import { Router } from "@angular/router";
import { ApiService } from "../services/service";
@Component({
  selector: "app-log-in-page",
  templateUrl: "./log-in-page.component.html",
  styleUrls: ["./log-in-page.component.css"],
})
export class LogInPageComponent implements OnInit {
  public loginForm: FormGroup;
  name:string;

  constructor(
    private api:ApiService,
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null,Validators.required),
      password: new FormControl(null,Validators.required),
    });
  }
  login() {
    this.http.get<any>("http://localhost:3000/Users").subscribe(
      (res) => {
        const user = res.find((a: any) => {
          
          console.log(res);
          return (
            a.username === this.loginForm.value.username &&
            a.password === this.loginForm.value.password
          );
        });
        
        if (user) {
          this.api.setUserId(user.id)
          this.api.setUserName(user.username)
          alert("Login Is Done Successfully");
          this.loginForm.reset();
          this.router.navigate(["dashboard"]);
        } else {
          alert("Invalid LogIn Credentials");
        }
      },
      (err) => {
        alert("Something went wrong");
      }
    );
  }
}
