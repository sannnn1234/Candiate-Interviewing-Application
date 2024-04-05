import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatfinalselectionComponent } from './matfinalselection.component';

describe('MatfinalselectionComponent', () => {
  let component: MatfinalselectionComponent;
  let fixture: ComponentFixture<MatfinalselectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatfinalselectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatfinalselectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
