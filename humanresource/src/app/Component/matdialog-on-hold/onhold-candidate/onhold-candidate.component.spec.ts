import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnholdCandidateComponent } from './onhold-candidate.component';

describe('OnholdCandidateComponent', () => {
  let component: OnholdCandidateComponent;
  let fixture: ComponentFixture<OnholdCandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnholdCandidateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnholdCandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
