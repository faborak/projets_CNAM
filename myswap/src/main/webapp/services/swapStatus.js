angular.module('swapStatus', [])

.service('swapStatus', [function() {

    "use strict";

    this.getLabelStatus = function(code) {
        var libelle = {};
        switch (code) {
            case 0:
                libelle = "En attente de réponse de votre interlocuteur";
                break;
            case 1:
                libelle = "En attente de réponse de votre part";
                break;
            case 2:
                libelle = "Nouvelle proposition Ã  étudier";
                break;
            case 3:
                libelle = "Proposition acceptée par les deux parties";
                break;
            case 4:
                libelle = "En attente d'envoi des objets";
                break;
            case 5:
                libelle = "En cours d'acheminement, c'ets pour bientot !";
                break;
            default:
                libelle = "Erreur de statut merci de contacter le service clientèle";
        }

        return libelle;

    };

}]);
