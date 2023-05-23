import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ODELJENJE_URL, ODELJENJA_ZA_BOLNICU_URL } from '../app.constants';
import { Odeljenje } from '../models/odeljenje';

@Injectable({
  providedIn: 'root'
})
export class OdeljenjeService {

  constructor(private httpClient: HttpClient) { }

    public getAllOdeljenje(): Observable<any>{
      return this.httpClient.get(ODELJENJE_URL);
  }

    public getAllOdeljenjaZaBolnicu(bolnicaId:number): Observable<any> {
      return this.httpClient.get(ODELJENJA_ZA_BOLNICU_URL + '/' + bolnicaId);
    }
    public addOdeljenje(Odeljenje: Odeljenje): Observable<any>{
      return this.httpClient.post(ODELJENJE_URL, Odeljenje);
  }
    public updateOdeljenje(Odeljenje: Odeljenje): Observable<any>{
      return this.httpClient.put(ODELJENJE_URL + "/" + Odeljenje.id, Odeljenje);
  }
    public deleteOdeljenje(idOdeljenje: number): Observable<any>{
      return this.httpClient.delete(ODELJENJE_URL + "/" + idOdeljenje);
    }
}
