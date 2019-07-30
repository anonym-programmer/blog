import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {

  formGroup: FormGroup;

  constructor(private http: HttpClient, private formBuilder: FormBuilder, private toastr: ToastrService) { 
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', Validators.required]
    });
  }

  change(oldPassword: String, newPassword: String): void {

    let url = '/api/admin/password';
    let data = { oldPassword: oldPassword, newPassword: newPassword };

    this.http.patch(url, data).subscribe(() => {
      this.toastr.success('You have successfully changed your password!', 'Success');
    }, (error) => {
      if (error.error.message != null) {
        this.toastr.error(error.error.message, 'Error');
      } else {
        this.toastr.error('Something went wrong!', 'Error');
      }
    })
  }
}
