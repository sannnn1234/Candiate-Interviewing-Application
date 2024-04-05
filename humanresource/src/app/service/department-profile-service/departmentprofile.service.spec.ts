import { TestBed } from '@angular/core/testing';

import { DepartmentprofileService } from './departmentprofile.service';

describe('DepartmentprofileService', () => {
  let service: DepartmentprofileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartmentprofileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
