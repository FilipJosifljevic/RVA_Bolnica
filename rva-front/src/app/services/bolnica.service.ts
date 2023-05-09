import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BOLNICA_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class BolnicaService {

  constructor(private httpClient: HttpClient) { }

    public getAllBolnica(): Observable<any>{
      return this.httpClient.get(BOLNICA_URL);
  }
}
