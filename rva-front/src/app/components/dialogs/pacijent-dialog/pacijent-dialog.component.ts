import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Odeljenje } from 'src/app/models/odeljenje';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { Pacijent } from 'src/app/models/pacijent';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { PacijentService } from 'src/app/services/pacijent.service';

@Component({
  selector: 'app-pacijent-dialog',
  templateUrl: './pacijent-dialog.component.html',
  styleUrls: ['./pacijent-dialog.component.css']
})
export class PacijentDialogComponent {
  odeljenja!: Odeljenje[];
  dijagnoze!: Dijagnoza[];
  public flag!: number;
  odeljenjeSubscription!: Subscription;
  dijagnozaSubscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<PacijentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Pacijent,
    public pacijentService: PacijentService,
    public odeljenjeService: OdeljenjeService,
    public dijagnozaService: DijagnozaService) { }

  ngOnDestroy(): void {
    this.odeljenjeSubscription.unsubscribe();
    this.dijagnozaSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.odeljenjeSubscription = this.odeljenjeService.getAllOdeljenje()
      .subscribe(odeljenja => {
        this.odeljenja = odeljenja;
      }),
    this.dijagnozaSubscription = this.dijagnozaService.getAllDijagnoza()
    .subscribe(dijagnoze => {
        this.dijagnoze = dijagnoze;
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }

  compareTo(a: any, b: any) {
    return a.id === b.id;
  }

  public add(): void {
    this.pacijentService.addPacijent(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno dodat pacijent!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public update(): void {
    this.pacijentService.updatePacijent(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno modifikovan pacijent!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public delete(): void {
    this.pacijentService.deletePacijent(this.data.id)
      .subscribe(() => {
        this.snackBar.open('Uspešno obrisan pacijent!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'Zatvori', {
      duration: 1500
    })
  }
}