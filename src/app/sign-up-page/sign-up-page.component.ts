import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { Validators, FormGroup, FormBuilder,FormControl } from "@angular/forms";
import { Router } from "@angular/router";
import { UserModel } from "../model/user.model";
@Component({
  selector: "app-sign-up-page",
  templateUrl: "./sign-up-page.component.html",
  styleUrls: ["./sign-up-page.component.css"],
})
export class SignUpPageComponent implements OnInit {
  public signupForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      fullname: new FormControl (null,Validators.required),
      username: new FormControl (null, Validators.required),
      email: new FormControl(null, Validators.required),
      password:new FormControl (null, Validators.required),
      mobile: new FormControl(null)
    });
  }
 
  signUp() {
    if (this.signupForm.valid) {
      let signUpRequestBody = this.signupForm?.value;

      signUpRequestBody["WishList"] = [];
      signUpRequestBody["Completed"] = [];
      this.http
        .post<UserModel>("http://localhost:3000/Users", signUpRequestBody)
        .subscribe(
          (res) => {
            alert("Sign Up Is Done Succesfully");
            this.signupForm?.reset();
            this.router.navigate(["login"]);
          },
          (err) => {
            alert("Something went wrong");
          }
        );
    }
    else{
      alert("Please fill required details ");
    }
  }
}
