import { Pipe, PipeTransform } from '@angular/core';
import { enquiry } from '../model/enquiry';

@Pipe({
  name: 'filterservice'
})
export class FilterservicePipe implements PipeTransform {

  transform(enquirylist: enquiry[], serviceType:string): enquiry[] {
    if(serviceType === 'All') 
      return enquirylist;
    
    return enquirylist.filter(enquiry => enquiry.serviceType === serviceType);
  }

}