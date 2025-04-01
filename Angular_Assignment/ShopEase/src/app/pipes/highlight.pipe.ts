import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'highlight'
})
export class HighlightPipe implements PipeTransform {

  transform(target: string): string {
    let terms=['Electronics',
    'Clothing & Fashion',
    'Home Appliances',
    'Beauty & Cosmetics',
    'Sports & Fitness'	
      ]
  
      terms.forEach(term => {
        const regex = new RegExp(`\\b${term}\\b`, 'gi');
        target = target.replace(regex, match => `'${match.toUpperCase()}'`);
      });
      return target;
    }

}