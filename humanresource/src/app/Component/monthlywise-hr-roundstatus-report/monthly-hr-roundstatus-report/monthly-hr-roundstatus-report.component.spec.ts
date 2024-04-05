import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyHrRoundstatusReportComponent } from './monthly-hr-roundstatus-report.component';

describe('MonthlyHrRoundstatusReportComponent', () => {
  let component: MonthlyHrRoundstatusReportComponent;
  let fixture: ComponentFixture<MonthlyHrRoundstatusReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlyHrRoundstatusReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonthlyHrRoundstatusReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
