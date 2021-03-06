import { Component, ViewChild, AfterViewInit, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { ItemData } from '../models/item-data';
import { ItemService } from '../services/item.service';
import { MatDialog } from '@angular/material/dialog';
import { ItemPopupComponent } from '../popups/item-popup/item-popup.component';
import { DeletePopupComponent } from '../popups/delete-popup/delete-popup.component';

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['itemName', 'itemCategory', 'price', 'stocksLeft', 'dealersDiscount', 'delete'];
  dataSource: ItemData[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  filter = "";
  

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private itemService: ItemService, public dialog: MatDialog) { }

  ngOnInit() {

  }
  ngAfterViewInit() {
    this.loadTable();
  }

  doFilter(value: string) {
    this.filter = value.trim();
    
    this.itemService.loadItemswithParam(
      this.sort.active, this.sort.direction, 0, this.paginator.pageSize, this.filter)
      .subscribe(data => {
        this.dataSource = data.itemList;
        this.resultsLength = data.itemCount;
        this.paginator.pageIndex = 0;
        this.isLoadingResults = false;
        this.isRateLimitReached = false;
        
      });
  }

  loadTable() {
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
        this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
      });


  }

  toggleNewItemEvent() {
    
    const dialogRef = this.dialog.open(ItemPopupComponent, {
      data: new ItemData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.isLoadingResults = true;
        this.itemService.addItem(result).subscribe(data => {
          this.dataSource = data.itemList;
          this.resultsLength = data.itemCount;
          this.paginator.pageIndex = 0;
          this.paginator.pageSize = 5;
          this.sort.active = "itemName";
          this.sort.direction = "asc";
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
        });
      }
    });
  }

  getRecord(row: ItemData) {
    const dialogRef = this.dialog.open(ItemPopupComponent, {
      data: row
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.isLoadingResults = true;
        this.itemService.addorUpdateItem(result, result.itemId).subscribe({
          next: data => {
            this.dataSource = data.itemList;
            this.resultsLength = data.itemCount;
            this.paginator.pageIndex = 0;
            this.paginator.pageSize = 5;
            this.sort.active = "itemName";
            this.sort.direction = "asc";
            this.isLoadingResults = false;
            this.isRateLimitReached = false;
          },
          error: error => {
            console.log(error);
          }
        })
      }
    });
  }

  redirectToDelete(item: any) {
    const dialogRef = this.dialog.open(DeletePopupComponent);

    dialogRef.afterClosed().subscribe(result => {
      
      if (result) {

        this.itemService.deleteItem(item.itemId).subscribe({
          next: data => {
            this.dataSource = data.itemList;
            this.resultsLength = data.itemCount;
            this.paginator.pageIndex = 0;
            this.paginator.pageSize = 5;
            this.sort.active = "itemName";
            this.sort.direction = "asc";
            this.isLoadingResults = false;
            this.isRateLimitReached = false;
            this.filter = "";
          },
          error: error => {
            console.log(error);
          }
        })
      }
    });
  }

}
