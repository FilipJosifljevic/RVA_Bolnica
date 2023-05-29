import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { DijagnozaDialogComponent } from '../dialogs/dijagnoza-dialog/dijagnoza-dialog.component';

@Component({
  selector: 'app-bolnica',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})

export class DijagnozaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'opis', 'oznaka', 'actions'];
  dataSourceDijagnoza!: MatTableDataSource<Dijagnoza>;
  
  constructor(private dijagnozaService: DijagnozaService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.subscription = this.dijagnozaService.getAllDijagnoza().subscribe(
      data => {
        //console.log(data);
        this.dataSourceDijagnoza = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, dijagnoza?: Dijagnoza): void {
    const dialogRef = this.dialog.open(DijagnozaDialogComponent, {data: (dijagnoza? dijagnoza: new Dijagnoza())});
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()})
  }

  ngOnDestroy(): void{
    this.subscription.unsubscribe;
  }

  ngOnChanges(){
    this.loadData();
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSourceDijagnoza.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}