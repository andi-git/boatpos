import {Component} from 'angular2/core';
import {Commitment} from './commitment';
import {CommitmentService} from "./commitment.service";
import {ConfigService} from "./config.service";
import {InfoService} from "./info.service";

@Component({
    selector: 'commitments',
    templateUrl: "commitments.component.html",
    styleUrls: ["commitments.component.css"]
})
export class CommitmentsComponent {

    private commitments:Commitment[];
    private selectedCommitment:Commitment;
    private subscription: any;

    constructor(private commitmentService:CommitmentService, private configService:ConfigService, private infoService:InfoService) {
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config =>
            this.commitmentService.getCommitments().subscribe(commitments => this.commitments= commitments)
        );
    }

    onSelect(commitment:Commitment) {
        if (!commitment.selected) {
            commitment.selected = true;
            this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde ausgewählt.");
        } else {
            commitment.selected = false;
            this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde entfernt.");
        }
    }
}