import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.scss']
})
export class AboutMeComponent {

  formGroup: FormGroup;

  details: String;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private toastr: ToastrService, private authService: AuthService) {
    this.view();
    this.createForm();
  }

  createForm(): void {
    this.formGroup = this.formBuilder.group({
      'editor': new FormControl(null)
    });
  }

  submit(): void {
    let htmlCode = { details: this.formGroup.get('editor').value };
    this.http.patch('http://localhost:8080/api/admin', htmlCode).subscribe(() => {
      this.view();
      this.toastr.success('Success', 'Successfully edited!');
    }, () => {
      this.toastr.error('Error', 'Something went wrong!');
    });
  }

  view(): void {
    this.http.get('http://localhost:8080/api/user').subscribe(res => {
      this.details = res['details'];
    });
  }
}
