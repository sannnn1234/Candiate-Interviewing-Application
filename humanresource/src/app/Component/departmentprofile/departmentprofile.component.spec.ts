import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentprofileComponent } from './departmentprofile.component';

describe('DepartmentprofileComponent', () => {
  let component: DepartmentprofileComponent;
  let fixture: ComponentFixture<DepartmentprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentprofileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
