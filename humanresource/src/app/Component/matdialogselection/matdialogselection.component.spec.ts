import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatdialogselectionComponent } from './matdialogselection.component';

describe('MatdialogselectionComponent', () => {
  let component: MatdialogselectionComponent;
  let fixture: ComponentFixture<MatdialogselectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatdialogselectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatdialogselectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
