import { Component, ViewChild } from '@angular/core';
import { ApexAxisChartSeries, ApexChart, ApexDataLabels, ApexFill, ApexLegend, ApexPlotOptions, ApexResponsive, ApexXAxis, ChartComponent } from 'ng-apexcharts';
import { ScheduleInterviewService } from 'src/app/service/schedule-interview/schedule-interview.service';




export type ChartOptions = {
  series: ApexAxisChartSeries | any;
  chart: ApexChart | any;
  dataLabels: ApexDataLabels | any;
  plotOptions: ApexPlotOptions | any;
  responsive: ApexResponsive[] | any;
  xaxis: ApexXAxis | any;
  legend: ApexLegend | any;
  fill: ApexFill | any;
};


@Component({
  selector: 'app-hr-report',
  templateUrl: './hr-report.component.html',
  styleUrls: ['./hr-report.component.css']
})
export class HrReportComponent {
  report: any[];
  d: Date = new Date();
  month: string = '';
  monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ];
  leftMonth() {
    this.d.setMonth(this.d.getMonth() - 1);
    this.month = this.monthNames[this.d.getMonth()];
    this.getHRreportBasedOnMonth();
  }

  rightMonth() {
    this.d.setMonth(this.d.getMonth() + 1);
    this.month = this.monthNames[this.d.getMonth()];
    this.getHRreportBasedOnMonth();
  }

  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;

  constructor(private scheduled: ScheduleInterviewService) {
    // this.d.setMonth(this.d.getMonth()+1);
    this.month = this.monthNames[this.d.getMonth()];

    this.chartOptions = {
      series: [],
      chart: {
        type: "bar",
        height: 350,
        stacked: true,
        toolbar: {
          show: true
        },
        zoom: {
          enabled: true
        }
      
      },
      responsive: [
        {
          breakpoint: 480,
          options: {
            legend: {
              position: "bottom",
              offsetX: -10,
              offsetY: 0
            }
          }
        }
      ],
      plotOptions: {
        bar: {
          horizontal: false
        }
      },
      xaxis: {
        type: "category",
        categories: []
      },
      legend: {
        position: "right",
        offsetY: 40
      },
      fill: {
        opacity: 1
      }
    };
  }

  ngOnInit(): void {
    this.getHRreportBasedOnMonth();
  }

  getHRreportBasedOnMonth() {
    const monthNumber = this.d.getMonth();
    let m = monthNumber.toString().padStart(2, '0');
    this.scheduled.getHRReport(m).subscribe(data => {
      const createdBySet = new Set();
      console.log(data);
      
      data.forEach(item => {
        createdBySet.add(item.fullName);
      });

      // Converting the Set to an array
      const categories = Array.from(createdBySet);

      // Initializing the series data
      const seriesData = {
        Selected: Array(categories.length).fill(0),
        Shortlisted: Array(categories.length).fill(0),
        Scheduled: Array(categories.length).fill(0),
        Rejected: Array(categories.length).fill(0)
      };

      // Populating the series data
      data.forEach(item => {
        const categoryIndex = categories.indexOf(item.fullName);

        if (item.roundStatus === 'Selected') {
          seriesData.Selected[categoryIndex] = item.count;
        } else if (item.roundStatus === 'Shortlisted') {
          seriesData.Shortlisted[categoryIndex] = item.count;
        } else if (item.roundStatus === 'Scheduled') {
          seriesData.Scheduled[categoryIndex] = item.count;
        }
        else if (item.roundStatus === 'Rejected') {
          seriesData.Rejected[categoryIndex] = item.count;
        }
      });


      this.chartOptions = {
        series: [
          { name: 'Selected', data: seriesData.Selected },
          { name: 'Shortlisted', data: seriesData.Shortlisted },
          { name: 'Scheduled', data: seriesData.Scheduled },
          { name: 'Rejected', data: seriesData.Rejected }
        ],
        chart: {
          type: "bar",
          height: 350,
          stacked: true,
          toolbar: {
            show: true
          },
          zoom: {
            enabled: true
          }
        },
        responsive: [
          {
            breakpoint: 480,
            options: {
              legend: {
                position: "bottom",
                offsetX: -10,
                offsetY: 0
              }
            }
          }
        ],
        plotOptions: {
          bar: {
            horizontal: false
          }
        },
        xaxis: {
          type: "category",
          categories: categories
        },
        legend: {
          position: "right",
          offsetY: 40
        },
        fill: {
          opacity: 1
        }
      };
     
    })
  }


}
