import {Component, NgZone} from 'angular2/core';
import {Commitment} from './commitment';
import {CommitmentService} from "./commitment.service";
import {InfoService} from "./info.service";
import {KeyBindingService} from "./keybinding.service";

@Component({
    selector: 'commitments',
    templateUrl: "commitments.component.html",
    styleUrls: ["commitments.component.css"]
})
export class CommitmentsComponent {

    constructor(private commitmentService:CommitmentService, private infoService:InfoService, private zone:NgZone, private keyBinding:KeyBindingService) {
    }

    ngOnInit() {
        let map:{[key: string] : ((e:ExtendedKeyboardEvent, combo:string) => any)} = {};
        // A...J
        for (var i = 65; i <= 74; i++) {
            map[String.fromCharCode(i)] = (e) => {
                this.zone.run(() => {
                    var commitment = this.commitmentService.getCommitmentByKeyBinding(String.fromCharCode(e.charCode));
                    if (commitment != null) {
                        this.click(commitment);
                    }
                });
            };
        }
        this.keyBinding.addBindingForMain(map);
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