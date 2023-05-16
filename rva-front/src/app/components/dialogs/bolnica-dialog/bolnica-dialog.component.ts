import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';

@Component({
  selector: 'app-bolnica-dialog',
  templateUrl: './bolnica-dialog.component.html',
  styleUrls: ['./bolnica-dialog.component.css']
})
export class BolnicaDialogComponent {
  public flagArtDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public BolnicaService: BolnicaService,
    @Inject(MAT_DIALOG_DATA) public dataBolnica: Bolnica,
    public dialogRef: MatDialogRef<BolnicaDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataBolnica.id + this.dataBolnica.naziv);
    this.BolnicaService.addBolnica(this.dataBolnica).subscribe(() => {
      this.snackBar.open('Uspesno dodata bolnica: ' + this.dataBolnica.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove bolnice. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.BolnicaService.updateBolnica(this.dataBolnica).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena bolnica: ' + this.dataBolnica.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene bolnice. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.BolnicaService.deleteBolnica(this.dataBolnica.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana bolnica: ' + this.dataBolnica.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja bolnice. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}