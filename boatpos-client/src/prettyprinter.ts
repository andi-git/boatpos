import {Injectable} from 'angular2/core';
import {isPresent} from "angular2/src/facade/lang";

@Injectable()
export class PrettyPrinter {

    pp2Pos(number:Number):string {
        let result:string = "";
        if (number < 10) {
            result += "0";
        }
        result += number;
        return result;
    }

    pp3Pos(number:Number):string {
        let result:string = "";
        if (number < 100) {
            result += "0";
        }
        result += this.pp2Pos(number);
        return result;
    }

    ppPrice(price:Number):string {
        let result:string = "";
        if (isPresent(price)) {
            result += price.toFixed(2);
        }
        return result;
    }
}
