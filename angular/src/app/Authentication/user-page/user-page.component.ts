import { Component, OnInit } from '@angular/core';
import { TokenstorageService } from 'src/app/tokenstorage.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  constructor(public tokenStorage:TokenstorageService) { }

  ngOnInit() {
  }

}
