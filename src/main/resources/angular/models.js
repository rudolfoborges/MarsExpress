'use strict';

var models = {};

(function(){
	
	models.Sonda = function(lancamento, nome){
		this.nome = nome;
		this.lancamento = lancamento;
		this.historico = [];
	};
	
})();