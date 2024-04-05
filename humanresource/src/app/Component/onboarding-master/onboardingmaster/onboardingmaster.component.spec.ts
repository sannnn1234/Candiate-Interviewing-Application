import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingmasterComponent } from './onboardingmaster.component';

describe('OnboardingmasterComponent', () => {
  let component: OnboardingmasterComponent;
  let fixture: ComponentFixture<OnboardingmasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OnboardingmasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OnboardingmasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
