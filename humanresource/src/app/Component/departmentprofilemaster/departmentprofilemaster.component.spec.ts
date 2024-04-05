import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentprofilemasterComponent } from './departmentprofilemaster.component';

describe('DepartmentprofilemasterComponent', () => {
  let component: DepartmentprofilemasterComponent;
  let fixture: ComponentFixture<DepartmentprofilemasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentprofilemasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentprofilemasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
