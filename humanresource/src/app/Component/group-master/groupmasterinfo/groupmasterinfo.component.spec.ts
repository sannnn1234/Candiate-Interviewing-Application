import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupmasterinfoComponent } from './groupmasterinfo.component';

describe('GroupmasterinfoComponent', () => {
  let component: GroupmasterinfoComponent;
  let fixture: ComponentFixture<GroupmasterinfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GroupmasterinfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroupmasterinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
