import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CustomerData } from '../models/customer-data';
import { CustomerTableData } from '../models/customer-table-data';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['customerName', 'remarks', 'latestDelivery', 'delete'];
  dataSource: CustomerData[] = [];
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    // this.dataSource.push(new CustomerTableData("Richard", "PickUp", "01-01-2020"));
    // this.dataSource.push(new CustomerTableData("Ally", "Delivery sa Paciano", "01-01-2020"));
    // this.dataSource.push(new CustomerTableData("Mark", "Delivery sa Lecheria", "01-01-2020"));
    // this.dataSource.push(new CustomerTableData("Grace", "Bogus Buyer", "--No pending order--"));
  }

  ngAfterViewInit() {
    this.loadTable();
  }

  loadTable() {
    this.customerService.loadCustomers().subscribe(result =>{
      console.log(result)
      this.dataSource = result.customerList;
    })
  }
}
