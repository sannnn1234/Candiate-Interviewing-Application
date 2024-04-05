import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateinterviewinformationComponent } from './updateinterviewinformation.component';

describe('UpdateinterviewinformationComponent', () => {
  let component: UpdateinterviewinformationComponent;
  let fixture: ComponentFixture<UpdateinterviewinformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateinterviewinformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateinterviewinformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
