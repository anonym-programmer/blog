import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent {

  public choice: any;
  public currentSection: String;

  constructor() {
    this.choice = '';
    this.currentSection = 'Default';
  }

  setValue(ch: any): void {
    this.choice = ch;
    switch (ch) {
      case 1: this.currentSection = 'About me'; break;
      case 2: this.currentSection = 'Change password'; break;
      case 3: this.currentSection = 'Posts management'; break;
      case 4: this.currentSection = 'IN PROGRESS'; break;
      default: this.currentSection = 'Default';
    }
  }
}
