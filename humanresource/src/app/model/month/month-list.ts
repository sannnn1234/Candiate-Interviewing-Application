export class MonthList {
    public monthList: Status[] = [
        { value: '1', viewValue: 'January' },
        { value: '2', viewValue: 'February' },
        { value: '3', viewValue: 'March' },
        { value: '4', viewValue: 'April' },
        { value: '5', viewValue: 'May' },
        { value: '6', viewValue: 'June' },
        { value: '7', viewValue: 'July' },
        { value: '8', viewValue: 'August' },
        { value: '9', viewValue: 'September' },
        { value: '10', viewValue: 'October' },
        { value: '11', viewValue: 'November' },
        { value: '12', viewValue: 'December' },
    ];
    public monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
}

export interface Status {
    value: string;
    viewValue: string;
}