import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileDetailsMasterComponent } from './profile-details-master.component';

describe('ProfileDetailsMasterComponent', () => {
  let component: ProfileDetailsMasterComponent;
  let fixture: ComponentFixture<ProfileDetailsMasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileDetailsMasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileDetailsMasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
