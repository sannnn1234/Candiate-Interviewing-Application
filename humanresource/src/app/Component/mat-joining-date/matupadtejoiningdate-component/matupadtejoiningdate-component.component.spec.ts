import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatupadtejoiningdateComponentComponent } from './matupadtejoiningdate-component.component';

describe('MatupadtejoiningdateComponentComponent', () => {
  let component: MatupadtejoiningdateComponentComponent;
  let fixture: ComponentFixture<MatupadtejoiningdateComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatupadtejoiningdateComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatupadtejoiningdateComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
