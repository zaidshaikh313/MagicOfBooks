import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { UserModel } from "../model/user.model";

@Injectable({
  providedIn: "root",
})
export class ApiService {
  constructor(private http: HttpClient) {}
  updatedPayload_completed: any;
  userCurrentCompleteList: any = [];
  userCurrentWishList: any = [];
  updatedPayload: any;
  username: string;
  userid: number;
  setUserName(name: string) {
    this.username = name;
  }

  setUserId(id: number) {
    this.userid = id;
  }


  getUserId() {
    return this.userid;
  }

  addtocomplete(userid, bookid) {
    this.http.get<any>("http://localhost:3000/Users/" + this.userid).subscribe(
      (res) => {
        // console.log(res);
        this.updatedPayload_completed = res;
        if (res?.Completed) {
          this.userCurrentCompleteList = res?.Completed;
        }

        if (this.userCurrentCompleteList?.indexOf(bookid) === -1) {
          this.userCurrentCompleteList.push(bookid);
          this.updatedPayload_completed["Completed"] =
            this.userCurrentCompleteList;
          alert("Book added in CompleteList");
          // console.log(this.updatedPayload_completed);
          this.updateCompleteList(this.updatedPayload_completed);
        } else {
          alert("Already added in CompleteList!");
        }
      },
      (err) => {
        alert("Something went wrong");
      }
    );
  }
  addtowish(userid, bookid) {
    this.http.get<any>("http://localhost:3000/Users/" + userid).subscribe(
      (res) => {
        // console.log(res);
        this.updatedPayload = res;
        if (res?.WishList) {
          this.userCurrentWishList = res?.WishList;
        }

        if (this.userCurrentWishList?.indexOf(bookid) === -1) {
          this.userCurrentWishList.push(bookid);
          this.updatedPayload["WishList"] = this.userCurrentWishList;

          // console.log(this.updatedPayload);
          this.updateCompleteList(this.updatedPayload);
          alert("Book Added to WishList");
        } else {
          alert("Already present in Wishlist");
        }
      },
      (err) => {
        alert("Something went wrong");
      }
    );
  }

  updateCompleteList(data) {
    console.log(this.userid);
    this.http
      .put<UserModel>("http://localhost:3000/Users/" + this.userid, data)
      .subscribe(
        (res) => {
          console.log("hii");
        },
        (err) => {
          alert("Something went wrong");
        }
      );
  }

  getBooks() {
    return this.http.get<any>("http://localhost:3000/Books").pipe(
      map((res: any) => {
        return res;
      })
    );
  }
}
