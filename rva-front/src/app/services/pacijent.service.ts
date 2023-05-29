import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PACIJENTI_ZA_ODELJENJE_URL, PACIJENT_URL } from '../app.constants';
import { Pacijent } from '../models/pacijent';

@Injectable({
  providedIn: 'root'
})
export class PacijentService {

  constructor(private httpClient: HttpClient) { }

    public getAllPacijent(): Observable<any>{
      return this.httpClient.get(PACIJENT_URL);
  }
    public getAllPacijentiZaOdeljenja(odeljenjeId: number): Observable<any>{
        return this.httpClient.get(PACIJENTI_ZA_ODELJENJE_URL + '/' + odeljenjeId);
    }
    public addPacijent(pacijent: Pacijent): Observable<any>{
      return this.httpClient.post(PACIJENT_URL, pacijent);
  }
    public updatePacijent(pacijent: Pacijent): Observable<any>{
      return this.httpClient.put(PACIJENT_URL + "/" + pacijent.id, pacijent);
  }
    public deletePacijent(idPacijent: number): Observable<any>{
      return this.httpClient.delete(PACIJENT_URL + "/" + idPacijent);
    }
}
