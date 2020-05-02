import {Injectable} from 'angular2/core';
import {isPresent} from "angular2/src/facade/lang";
import {isNumber} from "angular2/src/facade/lang";
import {Commitment} from "./model/commitment";
import {PromotionBefore} from "./model/promotion";
import {PromotionAfter} from "./model/promotion";

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

    printCommitments(commitments: Array<Commitment>): string {
        let commitmentString: string = "";
        if (isPresent(commitments)) {
            let first: boolean = true;
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

    //noinspection JSMethodCanBeStatic
    printPromotions(promotionBefore: PromotionBefore, promotionAfter: PromotionAfter): string {
        let promotionsString: string = "";
        if (isPresent(promotionBefore)) {
            promotionsString += promotionBefore.name;
        }
        if (isPresent(promotionBefore) && isPresent(promotionAfter)) {
            promotionsString += ", ";
        }
        if (isPresent(promotionAfter)) {
            promotionsString += promotionAfter.name;
        }
        return promotionsString;
    }

    printTime(date: Date): string {
        let timeString: string = "";
        if (isPresent(date) && date.getFullYear() > 1970) {
            return this.pp2Pos(date.getHours()) + ":" + this.pp2Pos(date.getMinutes()) + ":" + this.pp2Pos(date.getSeconds());
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
