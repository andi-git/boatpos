import {Income} from "./income";
export class Bill {

    cashBoxId: string;
    receiptIdentifier: string;
    receiptDateAndTime: Date;
    sumTaxSetNormal: number;
    sumTaxSetErmaessigt1: number;
    sumTaxSetErmaessigt2: number;
    sumTaxSetNull: number;
    sumTaxSetBesonders: number;
    encryptedTurnoverValue: string;
    signatureCertificateSerialNumber: string;
    signatureValuePreviousReceipt: string;
    company: Company;
    sumTotal: number;
    taxSetElements: Array<TaxSetElement> = [];
    sammelBeleg: Bill;
    sammelBelegStart: Date;
    sammelBelegEnd: Date;
    income: Income;
    dayReceipt: Bill;
    monthReceipt: Bill;
    yearReceipt: Bill;
    receiptType: string;
    jwsCompact: string;
    signatureDeviceAvailable: boolean;

    constructor(cashBoxId: string,
                receiptIdentifier: string,
                receiptDateAndTime: Date,
                sumTaxSetNormal: number,
                sumTaxSetErmaessigt1: number,
                sumTaxSetErmaessigt2: number,
                sumTaxSetNull: number,
                sumTaxSetBesonders: number,
                encryptedTurnoverValue: string,
                signatureCertificateSerialNumber: string,
                signatureValuePreviousReceipt: string,
                company: Company,
                sumTotal: number,
                taxSetElements: Array<TaxSetElement>,
                sammelBeleg: Bill,
                sammelBelegStart: Date,
                sammelBelegEnd: Date,
                income: Income,
                dayReceipt: Bill,
                monthReceipt: Bill,
                yearReceipt: Bill,
                receiptType: string,
                jwsCompact: string,
                signatureDeviceAvailable: boolean) {
        this.cashBoxId = cashBoxId;
        this.receiptIdentifier = receiptIdentifier;
        this.receiptDateAndTime = receiptDateAndTime;
        this.sumTaxSetNormal = sumTaxSetNormal;
        this.sumTaxSetErmaessigt1 = sumTaxSetErmaessigt1;
        this.sumTaxSetErmaessigt2 = sumTaxSetErmaessigt2;
        this.sumTaxSetNull = sumTaxSetNull;
        this.sumTaxSetBesonders = sumTaxSetBesonders;
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        this.signatureCertificateSerialNumber = signatureCertificateSerialNumber;
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        this.company = company;
        this.sumTotal = sumTotal;
        if (taxSetElements != null) {
            taxSetElements.forEach(taxSetElement => this.taxSetElements.push(taxSetElement));
        }
        this.sammelBeleg = sammelBeleg;
        this.sammelBelegStart = sammelBelegStart;
        this.sammelBelegEnd = sammelBelegEnd;
        this.income = income;
        this.dayReceipt = dayReceipt;
        this.monthReceipt = monthReceipt;
        this.yearReceipt = yearReceipt;
        this.receiptType = receiptType;
        this.jwsCompact = jwsCompact;
        this.signatureDeviceAvailable = signatureDeviceAvailable;
    }

    toString(): string {
        return JSON.stringify(this);
    }
}

export class Company {

    name: string;
    street: string;
    zip: string;
    city: string;
    country: string;
    phone: string;
    mail: string;
    atu: string;

    constructor(name: string,
                street: string,
                zip: string,
                city: string,
                country: string,
                phone: string,
                mail: string,
                atu: string) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.mail = mail;
        this.atu = atu;
    }

    toString(): string {
        return JSON.stringify(this);
    }
}

export class TaxSetElement {

    name: string;
    taxPercent: number;
    amount: number;
    pricePreTax: number;
    priceAfterTax: number;
    priceTax: number;

    constructor(name: string,
                taxPercent: number,
                amount: number,
                pricePreTax: number,
                priceAfterTax: number,
                priceTax: number) {
        this.name = name;
        this.taxPercent = taxPercent;
        this.amount = amount;
        this.pricePreTax = pricePreTax;
        this.priceAfterTax = priceAfterTax;
        this.priceTax = priceTax;
    }

    toString(): string {
        return JSON.stringify(this);
    }
}