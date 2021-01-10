import { OrderData } from "./order-data";

export class CustomerData{
    customerId: String;
    customerName: String;
    remarks: String;
    orders: OrderData[]; 
    latestDeliveryDate: String;
}