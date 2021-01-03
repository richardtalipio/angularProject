import { HttpClient } from '@angular/common/http';
import { Component, ViewChild, AfterViewInit, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, Observable, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { ItemData } from '../models/item-data';
import { ItemService } from '../services/item.service';

@Component({
  selector: 'app-inventory-test',
  templateUrl: './inventory-test.component.html',
  styleUrls: ['./inventory-test.component.css']
})
export class InventoryTestComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['itemName', 'variant', 'size', 'itemCategory', 'price', 'stocksLeft', 'dealersDiscount', 'delete'];
  dataSource: ItemData[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  filter = "";

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private itemService: ItemService) { }

  ngOnInit(){
   
  }
  ngAfterViewInit() {
    this.loadTable();
  }

  doFilter(value: string) {
    this.filter = value.trim();
    this.paginator.pageIndex= 0;
    this.loadTable();
  }

  loadTable(){
    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.itemService.loadItemswithParam(
            this.sort.active, this.sort.direction, this.paginator.pageIndex, this.paginator.pageSize, this.filter);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
          this.resultsLength = data.itemCount;

          return data.itemList;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          // Catch if the GitHub API has reached its rate limit. Return empty data.
          this.isRateLimitReached = true;
          return observableOf([]);
        })
      ).subscribe(data => {
        this.dataSource = data;
        this.sort.sortChange.subscribe(() =>  this.paginator.pageIndex= 0);
      });
  }

  
}
