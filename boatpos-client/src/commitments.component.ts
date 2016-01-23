import {Component} from 'angular2/core';
import {Commitment} from './commitment';
import {CommitmentService} from "./commitment.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'commitments',
    templateUrl: "commitments.component.html",
    styleUrls: ["commitments.component.css"]
})
export class CommitmentsComponent {

    constructor(private commitmentService:CommitmentService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        Mousetrap.bind(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'], (e) => {
            this.zone.run(() => {
                var commitment = this.commitmentService.getCommitmentByKeyBinding(String.fromCharCode(e.charCode));
                if (commitment != null) {
                    this.click(commitment);
                }
            });
        });
    }

    getCommitments() : Array<Commitment> {
        return this.commitmentService.getCommitments();
    }

    click(commitment:Commitment) {
        if (!commitment.selected) {
            commitment.selected = true;
            this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde ausgewählt.");
        } else {
            commitment.selected = false;
            this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde entfernt.");
        }
    }
}