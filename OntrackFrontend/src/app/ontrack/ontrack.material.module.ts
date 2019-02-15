import { NgModule } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule, MatGridListModule} from '@angular/material';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material';
import { MatProgressSpinnerModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
@NgModule({
    declarations: [],
    imports: [
    MatCardModule,
    CommonModule,
      MatButtonModule,
      FormsModule,
      MatIconModule,
      MatTableModule,
      MatProgressSpinnerModule,
      MatToolbarModule,
      MatMenuModule, MatFormFieldModule,
       MatInputModule, MatTooltipModule, MatSnackBarModule, MatDialogModule, MatSidenavModule, MatListModule,
       MatGridListModule,
       MatInputModule
      ] ,
      exports: [
        MatCardModule,
        MatButtonModule,
        MatIconModule,
        MatToolbarModule,
        MatMenuModule, MatFormFieldModule,

      MatInputModule, MatTooltipModule, MatSnackBarModule, MatDialogModule, MatSidenavModule, MatListModule,
          MatGridListModule,
          MatInputModule

         ]

   })
  export class OntrackMaterialModule { }