System.register(['angular2/core', "./commitment.service", "./config.service", "./info.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, commitment_service_1, config_service_1, info_service_1;
    var CommitmentsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            }],
        execute: function() {
            CommitmentsComponent = (function () {
                function CommitmentsComponent(commitmentService, configService, infoService) {
                    this.commitmentService = commitmentService;
                    this.configService = configService;
                    this.infoService = infoService;
                }
                CommitmentsComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.subscription = this.configService.isConfigured().subscribe(function (config) {
                        return _this.commitmentService.getCommitments().subscribe(function (commitments) { return _this.commitments = commitments; });
                    });
                };
                CommitmentsComponent.prototype.onSelect = function (commitment) {
                    if (!commitment.selected) {
                        commitment.selected = true;
                        this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde ausgewählt.");
                    }
                    else {
                        commitment.selected = false;
                        this.infoService.event().emit("Einsatz '" + commitment.name + "' wurde entfernt.");
                    }
                };
                CommitmentsComponent = __decorate([
                    core_1.Component({
                        selector: 'commitments',
                        templateUrl: "commitments.component.html",
                        styleUrls: ["commitments.component.css"]
                    }), 
                    __metadata('design:paramtypes', [commitment_service_1.CommitmentService, config_service_1.ConfigService, info_service_1.InfoService])
                ], CommitmentsComponent);
                return CommitmentsComponent;
            })();
            exports_1("CommitmentsComponent", CommitmentsComponent);
        }
    }
});
