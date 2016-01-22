import {Component} from 'angular2/core';
import {Commitment} from './commitment';
import {CommitmentService} from "./commitment.service";
import {ConfigService} from "./config.service";
import {InfoService} from "./info.service";
import {NgZone} from "angular2/core";

@Component({
    selector: 'commitments',
    templateUrl: "commitments.component.html",
    styleUrls: ["commitments.component.css"]
})
export class CommitmentsComponent {

    private commitments:Commitment[];
    private subscription: any;
    private zone:NgZone;

    constructor(private commitmentService:CommitmentService, private configService:ConfigService, private infoService:InfoService, private zone:NgZone) {
    }

    ngOnInit() {
        Mousetrap.bind(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'], (e) => {
            console.log(e.charCode);
            this.zone.run(() => {
                this.onSelect(this.getCommitmentByKeyBinding(String.fromCharCode(e.charCode)));
            });
        });
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

    getCommitmentByKeyBinding(keyBinding:string):Commitment {
        let commitment:Commitment = null;
        this.commitments.forEach((c) => {
            if (c.keyBinding == keyBinding) {
                commitment = c;
            }
        });
        return commitment;
    }
}