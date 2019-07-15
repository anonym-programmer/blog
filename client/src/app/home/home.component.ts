import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  secured() {
    this.http.get('http://localhost:8080/secured').subscribe((res) => {
      console.log(`IT WORKS => ${res}`);
    });
  }
}
