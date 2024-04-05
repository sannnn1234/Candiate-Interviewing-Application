import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacancymasterComponent } from './vacancymaster.component';

describe('VacancymasterComponent', () => {
  let component: VacancymasterComponent;
  let fixture: ComponentFixture<VacancymasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacancymasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VacancymasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
