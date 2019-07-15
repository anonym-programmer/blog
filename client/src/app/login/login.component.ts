import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { LoginDto } from '../shared/LoginDto.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  formGroup: FormGroup;
  dto = new LoginDto();

  constructor(private router: Router, private http: HttpClient, private formBuilder: FormBuilder) { 
    this.createForm();
  }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  login(username: String, password: String)  {
    let url = 'http://localhost:8080/login';

    this.dto.username = username;
    this.dto.password = password;

    this.http.post(url, this.dto).subscribe(isValid => {
      if (isValid) {
        
        this.http.get('http://localhost:8080/secured', {
          headers: new HttpHeaders()
          .set('Authorization', 'Basic ' + btoa(username + ':' + password))
        }).subscribe();

        this.router.navigate(['']);
      } else {
        alert("Authentication failed.");
      }
    });
  }
}
