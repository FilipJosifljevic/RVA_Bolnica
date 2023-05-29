import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';

@Component({
  selector: 'app-bolnica-dialog',
  templateUrl: './dijagnoza-dialog.component.html',
  styleUrls: ['./dijagnoza-dialog.component.css']
})
export class DijagnozaDialogComponent {
  public flagArtDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public DijagnozaService: DijagnozaService,
    @Inject(MAT_DIALOG_DATA) public dataDijagnoza: Dijagnoza,
    public dialogRef: MatDialogRef<DijagnozaDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataDijagnoza.id + this.dataDijagnoza.naziv);
    this.DijagnozaService.addDijagnoza(this.dataDijagnoza).subscribe(() => {
      this.snackBar.open('Uspesno dodata dijagnoza: ' + this.dataDijagnoza.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove dijagnoza. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.DijagnozaService.updateDijagnoza(this.dataDijagnoza).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena dijagnoza: ' + this.dataDijagnoza.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene dijagnoze. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.DijagnozaService.deleteDijagnoza(this.dataDijagnoza.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana dijagnoza: ' + this.dataDijagnoza.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja dijagnoze. ', 'Zatvori', {
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