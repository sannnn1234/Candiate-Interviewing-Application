import { TestBed } from '@angular/core/testing';

import { ProgrammasterService } from './programmaster.service';

describe('ProgrammasterService', () => {
  let service: ProgrammasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgrammasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
