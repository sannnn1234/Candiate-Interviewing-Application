import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmentroundmasterComponent } from './departmentroundmaster.component';

describe('DepartmentroundmasterComponent', () => {
  let component: DepartmentroundmasterComponent;
  let fixture: ComponentFixture<DepartmentroundmasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentroundmasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmentroundmasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
