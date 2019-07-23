import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { LocationStrategy } from '@angular/common';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.scss']
})
export class AboutMeComponent {

  formGroup: FormGroup;

  details: String;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private toastr: ToastrService, private url: LocationStrategy) {
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
    this.http.patch('/api/admin', htmlCode).subscribe(() => {
      this.view();
      this.toastr.success('Successfully edited!', 'Success');
    }, () => {
      this.toastr.error('Something went wrong!', 'Error');
    });
  }

  view(): void {
    this.http.get('/api/user').subscribe(res => {
      this.details = res['details'];
    });
  }

  isUrlNotAuth(): boolean {
    return this.url.path() === '/about-me';
  }
}
