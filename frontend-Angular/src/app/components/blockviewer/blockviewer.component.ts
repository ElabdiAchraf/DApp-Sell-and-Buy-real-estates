import { Component, Input } from '@angular/core';

enum BlockView {
  PREVIEW,
  CODE
}

@Component({
  selector: 'block-viewer',
  template: `
    <div class="block-section">

        <div class="block-content">
            <div [class]="containerClass" [ngStyle]="previewStyle" *ngIf="blockView == BlockView.PREVIEW">
                <ng-content></ng-content>
            </div>
            <div *ngIf="blockView == BlockView.CODE">
                <app-code lang="markup" ngPreserveWhitespaces>{{code}}
                </app-code>
            </div>
        </div>
    </div>
  `,
  styleUrls: ['./blockviewer.component.scss']
})
export class BlockViewer {

  @Input() header: string;

  @Input() code: string;

  @Input() containerClass: string;

  @Input() previewStyle: string;

  @Input() free: boolean = true;

  @Input() new: boolean = false;

  BlockView = BlockView;

  blockView: BlockView = BlockView.PREVIEW;

  activateView(event: Event, blockView: BlockView) {

    this.blockView = blockView;
    event.preventDefault();
  }

  async copyCode(event: Event) {
    await navigator.clipboard.writeText(this.code);
    event.preventDefault();
  }

}
