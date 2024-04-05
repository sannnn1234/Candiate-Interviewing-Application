import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDepartmentDetailsComponent } from './update-department-details.component';

describe('UpdateDepartmentDetailsComponent', () => {
  let component: UpdateDepartmentDetailsComponent;
  let fixture: ComponentFixture<UpdateDepartmentDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDepartmentDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateDepartmentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
