import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatfeedbacklistComponent } from './matfeedbacklist.component';

describe('MatfeedbacklistComponent', () => {
  let component: MatfeedbacklistComponent;
  let fixture: ComponentFixture<MatfeedbacklistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatfeedbacklistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatfeedbacklistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
