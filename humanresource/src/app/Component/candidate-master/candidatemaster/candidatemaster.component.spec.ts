import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatemasterComponent } from './candidatemaster.component';

describe('CandidatemasterComponent', () => {
  let component: CandidatemasterComponent;
  let fixture: ComponentFixture<CandidatemasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidatemasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CandidatemasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
