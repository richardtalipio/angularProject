import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { CustomerData } from '../models/customer-data';
import { CustomerTableData } from '../models/customer-table-data';
import { CustomerService } from '../services/customer.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {

 
}
