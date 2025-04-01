import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuBarComponent } from './common/menu-bar/menu-bar.component';
import { LoginComponent } from './common/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { ShopComponent } from './pages/shop/shop.component';
import { CartComponent } from './pages/cart/cart.component';

import { ContactComponent } from './pages/contact/contact.component';
import { ShopItemComponent } from './pages/shop/shop-item/shop-item.component';
import { EnquiriesComponent } from './pages/enquiries/enquiries.component';
import { EnquiryResponseComponent } from './pages/enquiries/enquiry-response/enquiry-response.component';
import { FilterservicePipe } from './pipes/filterservice.pipe';
import { HighlightPipe } from './pipes/highlight.pipe';


@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    LoginComponent,
    HomeComponent,
    ShopComponent,
    CartComponent,
  
    ContactComponent,
        ShopItemComponent,
        EnquiriesComponent,
        EnquiryResponseComponent,
        FilterservicePipe,
        HighlightPipe,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
