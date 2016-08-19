angular.module('swapStatus', [])

.service('swapStatus', [function() {

    "use strict";

    this.getLabelStatus = function(code) {
        var libellé = {};
        switch (code) {
            case 0:
                libellé = "En attente de réponse de votre interlocuteur";
                break;
            case 1:
                libellé = "En attente de réponse de votre part";
                break;
            case 2:
                libellé = "Nouvelle proposition à étudier";
                break;
            case 3:
                libellé = "Proposition acceptée par les deux parties";
                break;
            case 4:
                libellé = "En attente d'envoi des objets";
                break;
            case 5:
                libellé = "En cours d'acheminement, c'ets pour bientôt !";
                break;
            default:
                libellé = "Erreur de statut merci de contacter le service clientèle";
        }

        return libellé;

    };

}]);
