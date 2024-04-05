import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateemployeedetailsComponent } from './updateemployeedetails.component';

describe('UpdateemployeedetailsComponent', () => {
  let component: UpdateemployeedetailsComponent;
  let fixture: ComponentFixture<UpdateemployeedetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateemployeedetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateemployeedetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
