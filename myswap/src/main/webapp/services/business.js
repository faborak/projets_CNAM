angular.module('business', [])

.service('swapStatus', [function() {

    "use strict";

    this.getLabelStatus = function(idUser, deal) {
        var libelle = {};
        switch (deal.status.code) {
            case "En cours de création":
            	libelle = "En cours de création";
                break;
            case "En attente d'acceptation par le proposed":
            	if (deal.initiator.id == idUser){
            		libelle = "Votre interlocuteur n'a pas encore répondu !";
            	} else {
            		libelle = "Ce swap vous parait-il ok ?";
            	}
                break;
            case "En attente d'acceptation par l'initiateur":
            	if (deal.proposed.id == idUser){
            		libelle = "Votre interlocuteur n'a pas encore répondu !";
            	} else {
            		libelle = "Ce swap vous parait-il ok ?";
            	}
                break;    
            case "Transaction acceptée par l'initiateur":
            	if (deal.initiator.id == idUser){
            		libelle = "Transaction acceptée de votre côté ! Reste votre interlocuteur...";
            	} else {
            		libelle = "Transaction acceptée en face. Si vous acceptez aussi, le swap va avoir lieu.";
            	}
                break;
            case "Transaction acceptée par le proposed":
            	if (deal.initiator.id == idUser){
            		libelle = "Transaction acceptée en face. Si vous acceptez aussi, le swap va avoir lieu.";
            	} else {
            		libelle = "Transaction acceptée de votre côté ! Reste votre interlocuteur...";
            	}
                break;    
            case "Transaction mise à jour par l'initiateur":
            	if (deal.initiator.id == idUser){
            		libelle = "Votre interlocuteur n'a pas donné son avis sur votre dernière mise à jour !";
            	} else {
            		libelle = "Le swap a été mis à jour... Votre avis ?";
            	}
                break;
            case "Transaction mise à jour par le proposed":
            	if (deal.initiator.id == idUser){
            		libelle = "Le swap a été mis à jour... Votre avis ?";
            	} else {
            		libelle = "Votre interlocuteur n'a pas donné son avis sur la dernière mise à jour !";
            	}
                break;
            case "Transaction refusée par l'initiateur":
            	if (deal.initiator.id == idUser){
            		libelle = "Vous avez refusé ce swap.";
            	} else {
            		libelle = "Votre interlocuteur a refusé ce swap.";
            	}
                break;
            case "Transaction refusée par le proposed":
            	if (deal.initiator.id == idUser){
            		libelle = "Votre interlocuteur a refusé ce swap.";
            	} else {
            		libelle = "Vous avez refusé ce swap.";
            	}
                break;    
            case "Transaction validée":
            		libelle = "Transaction validée, swap en cours.";
                break;    
            default:
                libelle = "Erreur de statut, merci de contacter le service clientèle";
        }

        return libelle;

    };

}]);
