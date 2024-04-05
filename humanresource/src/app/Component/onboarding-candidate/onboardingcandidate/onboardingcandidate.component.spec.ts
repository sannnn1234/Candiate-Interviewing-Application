import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingcandidateComponent } from './onboardingcandidate.component';

describe('OnboardingcandidateComponent', () => {
  let component: OnboardingcandidateComponent;
  let fixture: ComponentFixture<OnboardingcandidateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnboardingcandidateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnboardingcandidateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
