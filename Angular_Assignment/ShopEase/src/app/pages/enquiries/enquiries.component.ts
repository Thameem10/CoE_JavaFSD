import { Component, OnInit } from '@angular/core';
import { enquiry } from '../../model/enquiry';  // Ensure correct casing
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-enquiries',
  templateUrl: './enquiries.component.html',
  styleUrls: ['./enquiries.component.css']  // Fix property name
})
export class EnquiriesComponent implements OnInit {
  enquirylist: enquiry[] = [];

  serviceTypes: string[] = [
    'Electronics',
    'Clothing & Fashion',
    'Home Appliances',
    'Beauty & Cosmetics',
    'Sports & Fitness'
  ];

  selected: string = 'All';

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.api.getEnquiries().subscribe({
      next: (response: enquiry[]) => {
        this.enquirylist = response;
      },
      error: (error) => {
        console.error('Error while fetching enquiries:', error);
      }
    });
  }
}
