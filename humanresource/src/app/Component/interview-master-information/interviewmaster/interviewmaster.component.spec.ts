import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewmasterComponent } from './interviewmaster.component';

describe('InterviewmasterComponent', () => {
  let component: InterviewmasterComponent;
  let fixture: ComponentFixture<InterviewmasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InterviewmasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterviewmasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
