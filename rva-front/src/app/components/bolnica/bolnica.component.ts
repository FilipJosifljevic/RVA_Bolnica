import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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
  dataSourceBolnica!: MatTableDataSource<Bolnica>;
  
  constructor(private bolnicaService: BolnicaService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.subscription = this.bolnicaService.getAllBolnica().subscribe(
      data => {
        //console.log(data);
        this.dataSourceBolnica = new MatTableDataSource(data);
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

  ngOnDestroy(): void{
    this.subscription.unsubscribe;
  }

  ngOnChanges(){
    this.loadData();
  }
}