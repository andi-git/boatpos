System.register(['angular2/core', "../../service/commitment.service", "../../service/info.service", "../../service/keybinding.service"], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, commitment_service_1, info_service_1, keybinding_service_1;
    var CommitmentsComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (commitment_service_1_1) {
                commitment_service_1 = commitment_service_1_1;
            },
            function (info_service_1_1) {
                info_service_1 = info_service_1_1;
            },
            function (keybinding_service_1_1) {
                keybinding_service_1 = keybinding_service_1_1;
            }],
        execute: function() {
            CommitmentsComponent = (function () {
                function CommitmentsComponent(commitmentService, infoService, zone, keyBinding) {
                    this.commitmentService = commitmentService;
                    this.infoService = infoService;
                    this.zone = zone;
                    this.keyBinding = keyBinding;
                }
                CommitmentsComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    var map = {};
                    // A...J
                    for (var i = 65; i <= 74; i++) {
                        map[String.fromCharCode(i)] = function (e) {
                            _this.zone.run(function () {
                                var commitment = _this.commitmentService.getCommitmentByKeyBinding(String.fromCharCode(e.charCode));
                                if (commitment != null) {
                                    _this.click(commitment);
                                }
                            });
                        };
                    }
                    this.keyBinding.addBindingForMain(map);
                };
                CommitmentsComponent.prototype.getCommitments = function () {
                    return this.commitmentService.getCommitments();
                };
                CommitmentsComponent.prototype.click = function (commitment) {
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
                        templateUrl: "html/component/rental/commitments.component.html",
                        styleUrls: ["css/component/rental/commitments.component.css"]
                    }), 
                    __metadata('design:paramtypes', [commitment_service_1.CommitmentService, info_service_1.InfoService, core_1.NgZone, keybinding_service_1.KeyBindingService])
                ], CommitmentsComponent);
                return CommitmentsComponent;
            })();
            exports_1("CommitmentsComponent", CommitmentsComponent);
        }
    }
});
