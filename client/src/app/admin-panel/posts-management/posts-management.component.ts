import { Component } from '@angular/core';

@Component({
  selector: 'app-posts-management',
  templateUrl: './posts-management.component.html',
  styleUrls: ['./posts-management.component.scss']
})
export class PostsManagementComponent {

  public choice: any;
  public currentSection: String;

  constructor() {
    this.choice = '';
    this.currentSection = 'Default';
  }

  setValue(ch: any): void {
    this.choice = ch;
    switch (ch) {
      case 1: this.currentSection = 'Add post'; break;
      case 2: this.currentSection = 'Edit posts'; break;
      default: this.currentSection = 'Default';
    }
  }
}
