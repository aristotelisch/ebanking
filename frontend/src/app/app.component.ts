import { Component } from '@angular/core';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  msgs = '';
  title = 'Ebanking';

  constructor(private messageService: MessageService) { }

  addSingle() {
    this.messageService.add({ severity: 'success', summary: 'Service Message', detail: '' });
  }

  addMultiple() {
    this.messageService.addAll([{ severity: 'success', summary: 'Service Message', detail: '' },
    { severity: 'info', summary: 'Info Message', detail: 'Via MessageService' }]);
  }

  clear() {
    this.messageService.clear();
  }

}
