import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatdialogrejectionComponent } from './matdialogrejection.component';

describe('MatdialogrejectionComponent', () => {
  let component: MatdialogrejectionComponent;
  let fixture: ComponentFixture<MatdialogrejectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatdialogrejectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatdialogrejectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
