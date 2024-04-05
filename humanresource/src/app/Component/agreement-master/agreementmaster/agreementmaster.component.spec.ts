import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgreementmasterComponent } from './agreementmaster.component';

describe('AgreementmasterComponent', () => {
  let component: AgreementmasterComponent;
  let fixture: ComponentFixture<AgreementmasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgreementmasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgreementmasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
