import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatedepartmentroundComponent } from './updatedepartmentround.component';

describe('UpdatedepartmentroundComponent', () => {
  let component: UpdatedepartmentroundComponent;
  let fixture: ComponentFixture<UpdatedepartmentroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatedepartmentroundComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatedepartmentroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
