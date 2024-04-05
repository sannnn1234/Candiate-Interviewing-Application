import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YearlyHRReportComponent } from './yearly-hrreport.component';

describe('YearlyHRReportComponent', () => {
  let component: YearlyHRReportComponent;
  let fixture: ComponentFixture<YearlyHRReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YearlyHRReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YearlyHRReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
