import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatevacancydetailsComponent } from './updatevacancydetails.component';

describe('UpdatevacancydetailsComponent', () => {
  let component: UpdatevacancydetailsComponent;
  let fixture: ComponentFixture<UpdatevacancydetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatevacancydetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatevacancydetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
