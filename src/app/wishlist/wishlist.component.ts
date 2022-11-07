import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ApiService } from "../services/service";

@Component({
  selector: "app-wishlist",
  templateUrl: "./wishlist.component.html",
  styleUrls: ["./wishlist.component.css"],
})
export class WishlistComponent implements OnInit {
  userid: Number;
  books: any = [];
  wishList: any = [];
  constructor(private api: ApiService, private http: HttpClient) {}

  ngOnInit(): void {
    this.userid = this.api.getUserId();
    this.getWishList();
  }

  async getWishList() {
    await this.http
      .get<any>("http://localhost:3000/Users/" + this.userid)
      .subscribe(
        (res) => {
          console.log(res);
          this.wishList = res?.WishList;
          console.log(this.wishList);
          this.getWishListBooks();
        },
        (err) => {
          alert("Something went wrong");
        }
      );
  }

  async getWishListBooks() {
    await this.wishList.forEach((element) => {
      this.http.get<any>("http://localhost:3000/Books/" + element).subscribe(
        (res) => {
          this.books?.push(res);
        },
        (err) => {
          alert("Something went wrong");
        }
      );
    });
  }
}
