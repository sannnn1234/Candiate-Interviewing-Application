import { TestBed } from '@angular/core/testing';

import { GroupmasterService } from './groupmaster.service';

describe('GroupmasterService', () => {
  let service: GroupmasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupmasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
