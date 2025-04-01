import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-enquiry-response',
  templateUrl: './enquiry-response.component.html',
  styleUrls: ['./enquiry-response.component.css'] // ✅ Corrected `styleUrls`
})
export class EnquiryResponseComponent implements OnInit {
  enquiryForm!: FormGroup;
  ids: string[] = [];
  emails: string[] = []; // ✅ Renamed `email` to `emails` to avoid confusion

  constructor(private fb: FormBuilder, private api: ApiService) {}

  ngOnInit() {
    this.enquiryForm = this.fb.group({
      subject: ['', Validators.required],
      message: ['', Validators.required],
      estimatedPrice: ['', Validators.required],
      duration: ['', Validators.required],
      email: [{ value: '', disabled: true }, Validators.required], // ✅ Fixed syntax
      messageId: ['', Validators.required]
    });

    this.api.getEnquiries().subscribe({
      next: (result: any) => {      
        this.ids = result.map((enquiry: any) => enquiry.id);
        this.emails = result.map((enquiry: any) => enquiry.email);
      },
      error: (error: any) => console.log(error)
    });
  }

  onIdChange(event: any) {
    const selectedId = event.target.value;
    
    const index = this.ids.findIndex(id => id === selectedId); // ✅ Ensure correct email selection
    if (index !== -1) {
      this.enquiryForm.controls['email'].enable(); // ✅ Enable before setting value
      this.enquiryForm.patchValue({ email: this.emails[index] });
    }
  }

  onSubmit() {
    if (this.enquiryForm.valid) {
      this.api.addEnquiryReply(this.enquiryForm.value).subscribe({
        next: (response: any) => {
          console.log('Enquiry response submitted successfully', response);
          alert('Enquiry response submitted successfully');
          this.enquiryForm.reset();
        },
        error: (error: any) => console.log(error)
      });
    }
  }
}
