import {Injectable} from 'angular2/core';
import {isPresent} from "angular2/src/facade/lang";
import {isNumber} from "angular2/src/facade/lang";
import {Commitment} from "./commitment";

@Injectable()
export class PrettyPrinter {

    pp2Pos(number:number):string {
        let result:string = "";
        if (number < 10) {
            result += "0";
        }
        result += number;
        return result;
    }

    pp3Pos(number:number):string {
        let result:string = "";
        if (number < 100) {
            result += "0";
        }
        result += this.pp2Pos(number);
        return result;
    }

    ppPrice(price:number, prefix:string):string {
        let result:string = isPresent(prefix) ? prefix : "€ ";
        if (isPresent(price) && isNumber(price) && !isNaN(price)) {
            result += price.toFixed(2);
        } else {
            result += "0.00";
        }
        return result;
    }

    printCommitments(commitments:Array<Commitment>):string {
        let commitmentString:string = "";
        if (isPresent(commitments)) {
            let first:boolean = true;
            commitments.forEach((commitment) => {
                if (!first) {
                    commitmentString += ", ";
                }
                commitmentString += commitment.name;
                first = false;
            });
        }
        return commitmentString;
    }
}
