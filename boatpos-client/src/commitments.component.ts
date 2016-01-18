import {Component} from 'angular2/core';
import {Commitment} from './commitment';
import {CommitmentService} from "./commitment.service";
import {ConfigService} from "./config.service";

@Component({
    selector: 'commitments',
    templateUrl: "commitments.component.html",
    styleUrls: ["commitments.component.css"]
})
export class CommitmentsComponent {

    private commitments:Commitment[];
    private selectedCommitment:Commitment;
    private subscription: any;

    constructor(private commitmentService:CommitmentService, private configService:ConfigService) {
    }

    getCommitments() {
        this.commitmentService.getCommitments().subscribe(commitments => this.commitments= commitments);
    }

    ngOnInit() {
        this.subscription = this.configService.isConfigured().subscribe(config => this.getCommitments());
    }

    onSelect(commitment:Commitment) {
        this.selectedCommitment = commitment;
    }
}