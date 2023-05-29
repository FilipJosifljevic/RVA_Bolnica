import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';
import { BolnicaDialogComponent } from '../dialogs/bolnica-dialog/bolnica-dialog.component';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})

export class BolnicaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa', 'budzet', 'actions'];
  dataSource!: MatTableDataSource<Bolnica>;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private bolnicaService: BolnicaService, private dialog: MatDialog, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.loadData();
  }
  ngOnChanges(): void { 
    this.loadData();
  }
  ngOnDestroy(): void{
    this.subscription.unsubscribe;
  }

  loadData(): void {
    this.subscription = this.bolnicaService.getAllBolnica().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, bolnica?: Bolnica): void {
    const dialogRef = this.dialog.open(BolnicaDialogComponent, {data: (bolnica? bolnica: new Bolnica())});
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()})
  }

 

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}