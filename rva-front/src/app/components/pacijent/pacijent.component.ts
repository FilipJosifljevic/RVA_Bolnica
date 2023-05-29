import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { PacijentService } from 'src/app/services/pacijent.service';
import { Odeljenje } from 'src/app/models/odeljenje';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { Pacijent } from 'src/app/models/pacijent';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PacijentDialogComponent } from '../dialogs/pacijent-dialog/pacijent-dialog.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-pacijent',
  templateUrl: './pacijent.component.html',
  styleUrls: ['./pacijent.component.css']
})
export class PacijentComponent {
  displayedColumns = ['id', 'ime', 'prezime', 'zdrOsiguranje', 'datumRodjenja', 'odeljenje', 'dijagnoza', 'actions'];
  dataSource!: MatTableDataSource<Pacijent>;
  subscription!: Subscription;
  @Input() selektovanoOdeljenje!: Odeljenje;

  constructor(private pacijentService: PacijentService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    if (this.selektovanoOdeljenje.id) {
      this.loadData();
    }
  }

  loadData() : void {
    
    this.subscription = this.pacijentService.getAllPacijentiZaOdeljenja(this.selektovanoOdeljenje.id)
      .subscribe(
        {
        next: (data) => {
          this.dataSource = new MatTableDataSource(data)
        },
        error: (error) => {
          this.snackBar.open('Nema pacijenata na odeljenju', 'Zatvori', {
            duration: 2500
          }); 
          this.dataSource = new MatTableDataSource<Pacijent>
        },
        complete: () => console.info('complete')
      })
  }
  public openDialog(flag: number, pacijent?: Pacijent) {
    const dialogRef = this.dialog.open(PacijentDialogComponent, { data: (pacijent ? pacijent : new Pacijent()) });
    dialogRef.componentInstance.flag = flag;
    if (flag === 1) {
      dialogRef.componentInstance.data.odeljenje = this.selektovanoOdeljenje;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result === 1) {
          this.loadData();
        }
      })
  }
  
}