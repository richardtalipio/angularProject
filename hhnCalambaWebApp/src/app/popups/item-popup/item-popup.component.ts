import { Component, OnInit, Inject, ChangeDetectionStrategy} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { ItemData } from '../../models/item-data';

@Component({
  selector: 'app-item-popup',
  templateUrl: './item-popup.component.html',
  styleUrls: ['./item-popup.component.css']
})
export class ItemPopupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ItemPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ItemData) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
