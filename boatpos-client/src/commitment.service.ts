import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {ObservableWrapper} from "angular2/src/facade/async";
import {Observable} from "rxjs/Observable";
import {Commitment} from "./commitment";

@Injectable()
export class CommitmentService {

    private commitmentsCache:Array<Commitment>;

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
        // when configuration is finished, load and cache commitments
        this.configService.isConfigured().subscribe((config) => {
            console.log("constructor of CommitmentService");
            this.loadCommitments().subscribe(commitments => this.commitmentsCache = commitments)
        });
    }

    getCommitments():Array<Commitment> {
        return this.commitmentsCache;
    }

    private loadCommitments():Observable<Array<Commitment>> {
        // call the rest-service
        return this.http.get(this.configService.getBackendUrl() + 'rest/commitment/enabled')
            // map the result to json
            .map(res => res.json())
            // map the result to Boat
            .map((commitments:Array<any>) => {
                let result:Array<Commitment> = [];
                if (commitments) {
                    commitments.forEach((commitment) => {
                        result.push(new Commitment(
                            commitment.id,
                            commitment.name,
                            commitment.enabled,
                            commitment.priority,
                            commitment.keyBinding,
                            commitment.pictureUrl,
                            commitment.pictureUrlThumb,
                            commitment.paper
                        ));
                    });
                }
                return result;
            });
    }

    getCommitmentByKeyBinding(keyBinding:string):Commitment {
        let commitment:Commitment = null;
        this.getCommitments().forEach((c) => {
            if (c.keyBinding == keyBinding) {
                commitment = c;
            }
        });
        return commitment;
    }

    getCommitmentByName(name:string):Commitment {
        let commitment:Commitment = null;
        this.getCommitments().forEach((c) => {
            if (c.name == name) {
                commitment = c;
            }
        });
        return commitment;
    }

    resetSelected() {
        this.getCommitments().forEach(commitment => commitment.selected = false);
    }

    getSelectedCommitmens():Array<Commitment> {
        let selectedCommitments:Array<Commitment> = [];
        this.getCommitments().forEach((commitment) => {
            if (commitment.selected === true) {
                selectedCommitments.push(commitment);
            }
        });
        return selectedCommitments;
    }
}