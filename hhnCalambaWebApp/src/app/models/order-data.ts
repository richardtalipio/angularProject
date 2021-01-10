import { OrderItemData } from "./order-item-data";

export class OrderData{
    orderId: String;
    priceLess: number;
    discount: number;
    grandTotal: number;
    profit: number;
    deliveryDate: Date;
    deliveryMethod: String;
    dateOrdered: Date;
    orderStatus: String;
    orderItems: OrderItemData[];
}