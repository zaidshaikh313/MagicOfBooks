import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { UserModel } from "../model/user.model";
import { ApiService } from "../services/service";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"],
})
export class DashboardComponent implements OnInit {
  books!: any;
  userid: number;
  username: string;

  constructor(private api: ApiService, private http: HttpClient) {}

  ngOnInit(): void {
    this.getBooks();
    this.getUserId();
  }
 
  getUserId() {
    this.userid = this.api.getUserId();
  }
  getBooks() {
    this.api.getBooks().subscribe((res) => {
      this.books = res;
    });
  }
  addtocomplete(userid, bookid) {
    this.api.addtocomplete(userid, bookid);
  }

  addtowish(userid, bookid) {
    this.api.addtowish(userid, bookid);
  }
}
