System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map', "./config.service", "../model/commitment"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, config_service_1, commitment_1;
    var CommitmentService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {},
            function (config_service_1_1) {
                config_service_1 = config_service_1_1;
            },
            function (commitment_1_1) {
                commitment_1 = commitment_1_1;
            }],
        execute: function() {
            CommitmentService = (function () {
                // constructors do dependency injection in Angular2
                function CommitmentService(http, configService) {
                    var _this = this;
                    this.http = http;
                    this.configService = configService;
                    // when configuration is finished, load and cache commitments
                    this.configService.isConfigured().subscribe(function (config) {
                        console.log("constructor of CommitmentService");
                        _this.loadCommitments().subscribe(function (commitments) { return _this.commitmentsCache = commitments; });
                    });
                }
                CommitmentService.prototype.getCommitments = function () {
                    return this.commitmentsCache;
                };
                CommitmentService.prototype.loadCommitments = function () {
                    // call the rest-service
                    return this.http.get(this.configService.getBackendUrl() + 'rest/commitment/enabled', { headers: this.configService.getDefaultHeader() })
                        .map(function (res) { return res.json(); })
                        .map(function (commitments) {
                        var result = [];
                        if (commitments) {
                            commitments.forEach(function (commitment) {
                                result.push(new commitment_1.Commitment(commitment.id, commitment.name, commitment.enabled, commitment.priority, commitment.keyBinding, commitment.pictureUrl, commitment.pictureUrlThumb, commitment.paper));
                            });
                        }
                        return result;
                    });
                };
                CommitmentService.prototype.getCommitmentByKeyBinding = function (keyBinding) {
                    var commitment = null;
                    this.getCommitments().forEach(function (c) {
                        if (c.keyBinding == keyBinding) {
                            commitment = c;
                        }
                    });
                    return commitment;
                };
                CommitmentService.prototype.getCommitmentByName = function (name) {
                    var commitment = null;
                    this.getCommitments().forEach(function (c) {
                        if (c.name == name) {
                            commitment = c;
                        }
                    });
                    return commitment;
                };
                CommitmentService.prototype.resetSelected = function () {
                    this.getCommitments().forEach(function (commitment) { return commitment.selected = false; });
                };
                CommitmentService.prototype.getSelectedCommitmens = function () {
                    var selectedCommitments = [];
                    this.getCommitments().forEach(function (commitment) {
                        if (commitment.selected === true) {
                            selectedCommitments.push(commitment);
                        }
                    });
                    return selectedCommitments;
                };
                CommitmentService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http, config_service_1.ConfigService])
                ], CommitmentService);
                return CommitmentService;
            }());
            exports_1("CommitmentService", CommitmentService);
        }
    }
});
