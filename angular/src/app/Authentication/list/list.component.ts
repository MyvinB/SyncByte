import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../ 	authentication.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
employeeId:String
trueTimeList:[];
public csvFileName = `test.csv`;
constructor(private authService:AuthenticationService,private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.params.subscribe(
      response => this.employeeId = response['employeeId']

    );
    this.authService.getTrueTime(this.employeeId).subscribe(
      (data:[])=>{
        console.log(data);
        this.trueTimeList=data;
      }
    )
  }


  getCSVDownloadLink() {

    return this.authService.generateCSVDownloadLink({
      filename: this.csvFileName,
      data: this.trueTimeList,
      columns: [
        'checkIn',
        'checkOut',
      ],
    });
  }

}
