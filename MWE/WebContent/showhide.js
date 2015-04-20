(function() {
var app = angular.module('tableBinder', []);
app.controller('tokenCtl', function($scope) {
	$scope.visible = 0;
	$scope.toggle = function(i) {
		$scope.visible = ($scope.visible == i) ? -1 : i;
	}
	$scope.isVisible = function(i) {
		return $scope.visible == i;
	}
	$scope.tokenStyle = function(i) {
		return ($scope.visible == i) ?  {color:'blue'} : {};
	}
	$scope.next = function() {
		$scope.toggle($scope.visible + 1);
	}
	$scope.prev = function() {
		$scope.toggle($scope.visible - 1);
	}
});

})();

function prefetchInflections(index) {
	$('table[index='+index+']').trigger('mouseover');
}

function addTips() {
	function text(x) {
		return x.html() || '';
	}

	function join(arr, delimiter) {
		return arr.filter(function(x){ return x.trim(); }).join(delimiter);
	}
	
	$('.token').each(function (index, value) {
		var row = $('.AnalysisTable[index=' + $(value).attr('index') + '] tr:eq(1)');
		var pos = text(row.find('.POS'));
		var prefix = text(row.find('.PREFIX')).replace('<br>','').trim();
		var construct = text(row.find('.CONSTRUCT'));
		var definiteness = text(row.find('.DEFINITENESS'));
		var suffix = (text(row.find('.SUFFIX')).replace(/<br>/g,' ') + ' ').split(' ')[0];
		$(value).find('span').text(join([join([prefix, pos], ' + '), construct, definiteness, suffix], ' - ')); 
	});
}
