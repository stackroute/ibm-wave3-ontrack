import { Ontrack } from './../../classes/ontrack';
import { SearchService } from './../../services/search.service';
import { OnTrackService } from './../../ontrack.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  service: any;
  products: Object;
  Ontrack: Object;
  productDetails = false;
  constructor(private route: Router, private onTrack: OnTrackService, private searchService: SearchService) { }
  ngOnInit() {
  //   console.log('Is this getting executed ..!!!');
   if (this.onTrack.cart !== undefined || this.onTrack.cart !== null) {
     this.productDetails = true;
     console.log(this.onTrack.cart);
     console.log(this.onTrack.cart, 'get me the status of the product !!');
     this.products = this.onTrack.cart;
   }
   // tslint:disable-next-line:one-line
   else {
     this.productDetails = false;
   }
  }
  continue() {
    this.route.navigate(['/' , 'continue']);
  }
  buy() {
    this.route.navigate(['/' , 'buy']);
  }

  cartToCard($event) {
    console.log($event);
    this.products = $event;
  }
}
