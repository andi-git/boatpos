import {Injectable} from 'angular2/core';
import {Http, Headers, HTTP_PROVIDERS} from 'angular2/http';
import 'rxjs/add/operator/map';
import {ConfigService} from "./config.service";
import {ObservableWrapper} from "angular2/src/facade/async";
import {Observable} from "rxjs/Observable";
import {Commitment} from "./commitment";

@Injectable()
export class CommitmentService {

    // constructors do dependency injection in Angular2
    constructor(private http:Http, private configService:ConfigService) {
    }

    getCommitments():Observable<Array<Commitment>> {
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
                            commitment.keyBinding
                        ));
                    });
                }
                return result;
            });
    }
}