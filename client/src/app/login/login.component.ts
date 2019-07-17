import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  formGroup: FormGroup;

  constructor(private router: Router, private http: HttpClient, private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  login(username: String, password: String): void {

    let url = 'http://localhost:8080/login';
    let data = { username: username, password: password };

    this.http.post(url, data).subscribe(() => {
      sessionStorage.setItem('Authorization', 'Basic ' + btoa(username + ':' + password));
      this.router.navigate(['']);
    }, () => {
      alert('Authentication failed.');
    });
  }
}
