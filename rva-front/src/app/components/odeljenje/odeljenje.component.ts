import { Component, Input, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Bolnica } from 'src/app/models/bolnica';
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
  selektovanoOdeljenje!: Odeljenje;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private odeljenjeService: OdeljenjeService, private dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit(): void { this.loadData(); }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
  ngOnChanges(): void {
    this.loadData();
  }

  loadData() {
    this.subscription = this.odeljenjeService.getAllOdeljenje().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        //sortiramo po ugnjezdenom obelezju
         this.dataSource.sortingDataAccessor = (row: Odeljenje, columnName: string): string => {
 
           console.log(row, columnName);
           if (columnName == "klinika") return row.klinika.naziv.toLocaleLowerCase();
           var columnValue = row[columnName as keyof Odeljenje] as unknown as string;
           return columnValue;
 
         }
 
         this.dataSource.sort = this.sort;
         //filtriranje po ugnjezdenom obelezju
         this.dataSource.filterPredicate = (data, filter: string) => {
           const accumulator = (currentTerm: any, key: string) => {
             return key === 'klinika' ? currentTerm + data.klinika.naziv : currentTerm + data[key as keyof Odeljenje];
           };
           const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
           const transformedFilter = filter.trim().toLowerCase();
           return dataStr.indexOf(transformedFilter) !== -1;
         };
 
         this.dataSource.paginator = this.paginator;
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

  selectRow(row: any) {
    this.selektovanoOdeljenje = row;
  }
  
  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}