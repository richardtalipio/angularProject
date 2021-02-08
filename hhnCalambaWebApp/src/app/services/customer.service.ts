import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerData } from '../models/customer-data';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient ) { 
  }

  loadCustomers(): Observable<any>{
    return  this.http.get<any>('/api/customer/loadCustomerTableData');
  }

  loadCustomersWithParam(sort: string, order: string, page: number, pageSize: number, filter: string): Observable<any>{
    return  this.http.get<any>('/api/customer/loadCustomersWithParam?sort='+sort+'&order='+order+'&page='+page+'&pageSize='+pageSize+'&filter='+filter);
  }

}
