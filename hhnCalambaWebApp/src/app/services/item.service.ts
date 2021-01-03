import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { ItemData } from '../models/item-data';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient ) { 
  }

  loadItems(): Observable<ItemData[]>{
    return  this.http.get<ItemData[]>('/api/item/loadItems');
  }

  addItem(item: ItemData): any{
    return  this.http.post<ItemData>('/api/item/addItem', item);
  }

  addorUpdateItem(item: ItemData, itemid: number): any{
    return  this.http.put<ItemData>('/api/item/updateItem/'+itemid, item);
  }

  deleteItem(itemid: number): any {
    return this.http.delete<ItemData>('/api/item/deleteItem/'+itemid);
  }

  loadItemswithParam(sort: string, order: string, page: number, pageSize: number, filter: string): Observable<any>{
    return  this.http.get<any>('/api/item/loadItemswithParam?sort='+sort+'&order='+order+'&page='+page+'&pageSize='+pageSize+'&filter='+filter);
  }


}
