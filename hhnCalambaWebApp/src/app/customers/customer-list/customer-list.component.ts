import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { CustomerData } from '../../models/customer-data'
import { CustomerTableData } from '../../models/customer-table-data';
import { CustomerService } from '../../services/customer.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['customerName', 'remarks', 'latestDeliveryDate', 'delete'];
  dataSource: CustomerData[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  filter = "";

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {

  }

  ngAfterViewInit() {
    this.loadTable();
  }

  loadTable() {
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.customerService.loadCustomersWithParam(
            this.sort.active, this.sort.direction, this.paginator.pageIndex, this.paginator.pageSize, this.filter);
        }),
        map(data => {
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
          this.resultsLength = data.customerCount;

          return data.customerList;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          // Catch if the GitHub API has reached its rate limit. Return empty data.
          this.isRateLimitReached = true;
          return observableOf([]);
        })
      ).subscribe(data => {
        this.dataSource = data;
        this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
      });
  }

  doFilter(value: string) {
    this.filter = value.trim();
    
    this.customerService.loadCustomersWithParam(
      this.sort.active, this.sort.direction, 0, this.paginator.pageSize, this.filter)
      .subscribe(data => {
        this.dataSource = data.customerList;
        this.resultsLength = data.customerCount;
        this.paginator.pageIndex = 0;
        this.isLoadingResults = false;
        this.isRateLimitReached = false;
        
      });
  }

}
