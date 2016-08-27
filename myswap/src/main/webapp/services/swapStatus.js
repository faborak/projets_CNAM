angular.module('swapStatus', [])

.service('swapStatus', [function() {

    "use strict";

    this.getLabelStatus = function(code) {
        var libelle = {};
        switch (code) {
            case 0:
                libelle = "En attente de r�ponse de votre interlocuteur";
                break;
            case 1:
                libelle = "En attente de r�ponse de votre part";
                break;
            case 2:
                libelle = "Nouvelle proposition à �tudier";
                break;
            case 3:
                libelle = "Proposition accept�e par les deux parties";
                break;
            case 4:
                libelle = "En attente d'envoi des objets";
                break;
            case 5:
                libelle = "En cours d'acheminement, c'ets pour bientot !";
                break;
            default:
                libelle = "Erreur de statut merci de contacter le service client�le";
        }

        return libelle;

    };

}]);
