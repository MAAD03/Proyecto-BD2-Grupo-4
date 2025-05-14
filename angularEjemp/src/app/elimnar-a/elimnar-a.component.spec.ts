import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElimnarAComponent } from './elimnar-a.component';

describe('ElimnarAComponent', () => {
  let component: ElimnarAComponent;
  let fixture: ComponentFixture<ElimnarAComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ElimnarAComponent]
    });
    fixture = TestBed.createComponent(ElimnarAComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
