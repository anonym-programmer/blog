import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.scss']
})
export class AboutMeComponent {
 
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.formGroup = this.formBuilder.group({
      'editor': new FormControl(null)
    });
  }

  submit(): void {
    
  }
}
