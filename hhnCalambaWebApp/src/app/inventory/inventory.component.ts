import { HttpClient } from '@angular/common/http';
import { Component, ViewChild, AfterViewInit, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { shareReplay } from 'rxjs/operators';
import { ItemData } from '../models/item-data';
import { ItemService } from '../services/item.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ItemPopupComponent } from '../popups/item-popup/item-popup.component'

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['itemName', 'variant', 'size', 'itemCategory', 'price', 'stocksLeft', 'dealersDiscount', 'delete'];

  public dataSource = new MatTableDataSource<ItemData>();
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;
  itemData: ItemData;
  getAllItems$ = this.itemService.loadItems().pipe(shareReplay(5));

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private itemService: ItemService, public dialog: MatDialog) { }

  public getAllItems = () => {
    this.getAllItems$.subscribe(data => {
      this.isLoadingResults = false;
      this.dataSource.data = data as ItemData[];
      console.log(data)
    });
  }

  ngOnInit() {
    this.isLoadingResults = true;
    this.getAllItems();
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  toggleNewItemEvent() {
    const dialogRef = this.dialog.open(ItemPopupComponent, {
      data: new ItemData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.dataSource.data.push(result);
        this.dataSource.data = this.dataSource.data;

        this.itemService.addItem(result).subscribe({
          next: data => {
            console.log("Added Successfully");
          },
          error: error => {
            console.log(error);
          }
        })
      }
    });
  }

  redirectToDelete(id: String) {

  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  getRecord(row: ItemData) {
    const dialogRef = this.dialog.open(ItemPopupComponent, {
      data: row
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result) {
        this.dataSource.data = this.dataSource.data;

        this.itemService.addorUpdateItem(result, result.itemId).subscribe({
          next: data => {
            console.log("Added Successfully");
          },
          error: error => {
            console.log(error);
          }
        })
      }
    });
  }

  
}
