import {Injectable} from "angular2/core";
import {isPresent, isNumber} from "angular2/src/facade/lang";

@Injectable()
export class PrettyPrinter {

    //noinspection JSMethodCanBeStatic
    pp2Pos(number: number): string {
        let result: string = "";
        if (number < 10) {
            result += "0";
        }
        result += number;
        return result;
    }

    pp3Pos(number: number): string {
        let result: string = "";
        if (number < 100) {
            result += "0";
        }
        result += this.pp2Pos(number);
        return result;
    }

    //noinspection JSMethodCanBeStatic
    ppPrice(price: number, prefix?: string): string {
        let result: string = isPresent(prefix) ? prefix : "€ ";
        if (isPresent(price) && isNumber(price) && !isNaN(price)) {
            result += price.toFixed(2);
        } else {
            result += "0.00";
        }
        return result;
    }

    printTime(date: Date): string {
        let timeString: string = "";
        if (isPresent(date) && date.getUTCFullYear() > 1970) {
            return this.pp2Pos(date.getUTCHours()) + ":" + this.pp2Pos(date.getUTCMinutes()) + ":" + this.pp2Pos(date.getUTCSeconds());
        }
        return timeString;
    }

    printDate(date: Date): string {
        let dateString: string = "";
        if (isPresent(date) && date.getFullYear() > 1970) {
            return this.pp2Pos(date.getDate()) + "." + this.pp2Pos(date.getMonth() + 1) + "." + date.getFullYear();
        }
        return dateString;
    }

    printDateAndTime(date: Date): string {
        return this.printDate(date) + " " + this.printTime(date);
    }

    //noinspection JSMethodCanBeStatic
    ppFixLength(text: string, length: number, align: Align): string {
        let result: string = text;
        if (text.length > length) {
            result = text.substr(0, length);
        } else {
            if (align === Align.LEFT || align === Align.CENTER) {
                for (let i: number = text.length; i < length; i++) {
                    result += " ";
                }
            } else {
                for (let i: number = text.length; i < length; i++) {
                    result = " " + result;
                }
            }
        }
        return result;
    }
}

export enum Align {
    LEFT,
    CENTER,
    RIGHT
}
