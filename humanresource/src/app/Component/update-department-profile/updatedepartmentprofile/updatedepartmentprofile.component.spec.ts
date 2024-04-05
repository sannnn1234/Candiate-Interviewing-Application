import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatedepartmentprofileComponent } from './updatedepartmentprofile.component';

describe('UpdatedepartmentprofileComponent', () => {
  let component: UpdatedepartmentprofileComponent;
  let fixture: ComponentFixture<UpdatedepartmentprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatedepartmentprofileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatedepartmentprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
