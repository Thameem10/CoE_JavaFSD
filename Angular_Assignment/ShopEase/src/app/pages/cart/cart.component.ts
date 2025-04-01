import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: any[] = [];
  totalPrice: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.cart = this.cartService.getCart();
    this.updateTotal();
  }

  addToCart(product: any) {
    this.cartService.addToCart(product);
    this.cart = this.cartService.getCart();
    this.updateTotal();
  }

  removeFromCart(productId: number) {
    this.cartService.removeFromCart(productId);
    this.cart = this.cartService.getCart();
    this.updateTotal();
  }

  updateTotal() {
    this.totalPrice = this.cartService.getTotalPrice();
  }
}
