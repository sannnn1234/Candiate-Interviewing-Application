import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateagreementlistComponent } from './updateagreementlist.component';

describe('UpdateagreementlistComponent', () => {
  let component: UpdateagreementlistComponent;
  let fixture: ComponentFixture<UpdateagreementlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateagreementlistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateagreementlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
