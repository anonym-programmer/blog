import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  formGroup: FormGroup;

  constructor(private router: Router, private http: HttpClient, private formBuilder: FormBuilder, private toastr: ToastrService) {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(username: String, password: String): void {

    let url = 'http://localhost:8080/login';
    let data = { username: username, password: password };

    this.http.post(url, data).subscribe(() => {
      sessionStorage.setItem('Authorization', 'Basic ' + btoa(username + ':' + password));
      this.toastr.success('You have successfully logged in!', 'Success');
      this.router.navigate(['admin-panel']);
    }, () => {
      this.toastr.error('Authentication failed!', 'Error', { timeOut: 3000 });
    });
  }
}
