import { TestBed } from '@angular/core/testing';

import { InterviewInfoService } from './interview-info.service';

describe('InterviewInfoService', () => {
  let service: InterviewInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterviewInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
