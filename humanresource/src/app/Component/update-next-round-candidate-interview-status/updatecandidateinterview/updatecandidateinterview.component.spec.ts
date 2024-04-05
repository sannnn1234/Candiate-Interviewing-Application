import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatecandidateinterviewComponent } from './updatecandidateinterview.component';

describe('UpdatecandidateinterviewComponent', () => {
  let component: UpdatecandidateinterviewComponent;
  let fixture: ComponentFixture<UpdatecandidateinterviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatecandidateinterviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatecandidateinterviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
