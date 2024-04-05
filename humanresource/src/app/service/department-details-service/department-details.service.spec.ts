import { TestBed } from '@angular/core/testing';

import { DepartmentDetailsService } from './department-details.service';

describe('DepartmentDetailsService', () => {
  let service: DepartmentDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartmentDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
