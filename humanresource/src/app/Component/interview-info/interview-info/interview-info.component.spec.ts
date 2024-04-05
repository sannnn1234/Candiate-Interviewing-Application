import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterviewInfoComponent } from './interview-info.component';

describe('InterviewInfoComponent', () => {
  let component: InterviewInfoComponent;
  let fixture: ComponentFixture<InterviewInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InterviewInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterviewInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
