import { Component, OnInit } from '@angular/core';
import { ApiService } from "../services/service";
import { HttpClient } from "@angular/common/http";
@Component({
  selector: 'app-guest-dashboard',
  templateUrl: './guest-dashboard.component.html',
  styleUrls: ['./guest-dashboard.component.css']
})
export class GuestDashboardComponent implements OnInit {
  books!: any;
  constructor(private api: ApiService, private http: HttpClient) { }

  ngOnInit(): void {
    this.getBooks();
  }
  getBooks() {
    this.api.getBooks().subscribe((res) => {
      this.books = res;
    });
  }
}
