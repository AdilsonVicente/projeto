var AnaliseSkillus = AnaliseSkillus || {};

AnaliseSkillus.GraficoVendaCiclo = (function() {
	function GraficoVendaCiclo() {
		this.ctx = $('#graficoVendaCiclo')[0].getContext('2d');
	}
	
	GraficoVendaCiclo.prototype.iniciar = function() {
		$.ajax({
			url: '/total',
			method: 'GET',
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaCiclo) {
		var tipo[];
		var valores [];
		vendaCiclo.forEach(function(obj) {
			tipo.unshift(obj.tipo);
			valores.unshift(obj.total);
		});
		
		var graficoVendaCiclo = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: tipo,
				datasets: [{
					label: 'Vendas por ciclo',
					backgroundColor: "rgba(26,179,148,0.5)",
					pointBorderColor: "rgba(26,179,148,1)",
					pointBackgroundColor: "#fff",
					data: valores
				}]
			},
		});
	}
	
	return GraficoVendaCiclo;
}());

$(function() {
	var graficoVendaCiclo = new AnaliseSkillus.GraficoVendaCiclo();
	graficoVendaCiclo.iniciar();
});