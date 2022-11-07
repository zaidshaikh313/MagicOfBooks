import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ApiService } from "../services/service";

@Component({
  selector: "app-completed-books",
  templateUrl: "./completed-books.component.html",
  styleUrls: ["./completed-books.component.css"],
})
export class CompletedBooksComponent implements OnInit {
  userid: Number;
  books: any = [];
  completeList: any = [];
  constructor(private api: ApiService, private http: HttpClient) {}

  ngOnInit(): void {
    this.userid = this.api.getUserId();
    this.getCompleteList();
  }

  async getCompleteList() {
    await this.http
      .get<any>("http://localhost:3000/Users/" + this.userid)
      .subscribe(
        (res) => {
          console.log(res);
          this.completeList = res?.Completed;
          console.log(this.completeList);
          this.getCompleteListBooks();
        },
        (err) => {
          alert("Something went wrong");
        }
      );
  }

  async getCompleteListBooks() {
    await this.completeList.forEach((element) => {
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
