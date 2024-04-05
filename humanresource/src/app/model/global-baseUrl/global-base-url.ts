export class GlobalBaseUrl {

    public baseUrl: string = window["cfgApiBaseUrl" as keyof Window] ;

    public formatDate(date: Date) {
        return [
            date.getFullYear(),
            this.padTo2Digits(date.getMonth() + 1),
            this.padTo2Digits(date.getDate()),
        ].join('-');
    }

    public padTo2Digits(num: number) {
        return num.toString().padStart(2, '0');
    }

    public status: Status[] = [
        { value: 'Y', viewValue: 'Active' },
        { value: 'N', viewValue: 'In-Active' },
        ];
        
    public statusAll: Status[] = [
        { value: 'All', viewValue: 'All' },
        { value: 'Y', viewValue: 'Active' },
        { value: 'N', viewValue: 'In-Active' },
        ];
}
export interface Status {
    value: string;
    viewValue: string;
   }
   