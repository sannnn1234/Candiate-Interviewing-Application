import { TestBed } from '@angular/core/testing';

import { EmployeegroupService } from './employeegroup.service';

describe('EmployeegroupService', () => {
  let service: EmployeegroupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeegroupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
