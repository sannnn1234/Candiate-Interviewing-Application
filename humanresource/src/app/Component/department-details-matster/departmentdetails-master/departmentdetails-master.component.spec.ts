import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentdetailsMasterComponent } from './departmentdetails-master.component';

describe('DepartmentdetailsMasterComponent', () => {
  let component: DepartmentdetailsMasterComponent;
  let fixture: ComponentFixture<DepartmentdetailsMasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentdetailsMasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentdetailsMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
