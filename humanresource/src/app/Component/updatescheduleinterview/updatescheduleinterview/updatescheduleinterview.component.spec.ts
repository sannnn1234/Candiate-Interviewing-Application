import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatescheduleinterviewComponent } from './updatescheduleinterview.component';

describe('UpdatescheduleinterviewComponent', () => {
  let component: UpdatescheduleinterviewComponent;
  let fixture: ComponentFixture<UpdatescheduleinterviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatescheduleinterviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatescheduleinterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
