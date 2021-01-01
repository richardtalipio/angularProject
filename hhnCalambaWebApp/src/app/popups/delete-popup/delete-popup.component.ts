import { Component, OnInit, Inject, ChangeDetectionStrategy} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-delete-popup',
  templateUrl: './delete-popup.component.html',
  styleUrls: ['./delete-popup.component.css']
})

export class DeletePopupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeletePopupComponent>) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
