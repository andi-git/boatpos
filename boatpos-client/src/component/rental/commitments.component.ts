import {Component, NgZone} from 'angular2/core';
import {Commitment} from '../../model/commitment';
import {CommitmentService} from "../../service/commitment.service";
import {InfoService} from "../../service/info.service";
import {KeyBindingService} from "../../service/keybinding.service";

@Component({
    selector: 'commitments',
    templateUrl: "../../../html/component/rental/commitments.component.html",
    styleUrls: ["../../../css/component/rental/commitments.component.css"]
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