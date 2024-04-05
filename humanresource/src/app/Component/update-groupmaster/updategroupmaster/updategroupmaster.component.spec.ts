import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdategroupmasterComponent } from './updategroupmaster.component';

describe('UpdategroupmasterComponent', () => {
  let component: UpdategroupmasterComponent;
  let fixture: ComponentFixture<UpdategroupmasterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdategroupmasterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdategroupmasterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
