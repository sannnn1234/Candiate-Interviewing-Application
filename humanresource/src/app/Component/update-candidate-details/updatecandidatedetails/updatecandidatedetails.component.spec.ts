import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatecandidatedetailsComponent } from './updatecandidatedetails.component';

describe('UpdatecandidatedetailsComponent', () => {
  let component: UpdatecandidatedetailsComponent;
  let fixture: ComponentFixture<UpdatecandidatedetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatecandidatedetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatecandidatedetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
