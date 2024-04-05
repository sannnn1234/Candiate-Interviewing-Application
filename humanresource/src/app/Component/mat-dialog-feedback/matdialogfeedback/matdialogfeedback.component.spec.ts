import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatdialogfeedbackComponent } from './matdialogfeedback.component';

describe('MatdialogfeedbackComponent', () => {
  let component: MatdialogfeedbackComponent;
  let fixture: ComponentFixture<MatdialogfeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatdialogfeedbackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatdialogfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
