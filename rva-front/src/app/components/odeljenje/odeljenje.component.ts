import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Odeljenje } from 'src/app/models/odeljenje';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { OdeljenjeDialogComponent } from '../dialogs/odeljenje-dialog/odeljenje-dialog.component';

@Component({
  selector: 'app-odeljenje',
  templateUrl: './odeljenje.component.html',
  styleUrls: ['./odeljenje.component.css']
})
export class OdeljenjeComponent {
  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'lokacija', 'klinika','actions'];
  dataSource!: MatTableDataSource<Odeljenje>;

  constructor(private odeljenjeService: OdeljenjeService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.odeljenjeService.getAllOdeljenje().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data)
        console.log(data)
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, odeljenje?: Odeljenje): void {
    const dialogRef = this.dialog.open(OdeljenjeDialogComponent, { data: (odeljenje ? odeljenje : new Odeljenje()) });
    //otvara modalni dijalog odgovarajuće komponente
    //vracamo instancu keirane komponente dialoga
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}