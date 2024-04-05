import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentRoundInformationComponent } from './department-round-information.component';

describe('DepartmentRoundInformationComponent', () => {
  let component: DepartmentRoundInformationComponent;
  let fixture: ComponentFixture<DepartmentRoundInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentRoundInformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentRoundInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
