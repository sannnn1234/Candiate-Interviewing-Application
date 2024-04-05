import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateOnboardingComponent } from './update-onboarding.component';

describe('UpdateOnboardingComponent', () => {
  let component: UpdateOnboardingComponent;
  let fixture: ComponentFixture<UpdateOnboardingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateOnboardingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateOnboardingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
