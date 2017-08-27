'use strict';

/**
 * @ngdoc function
 * @name demoApp.controller:CartCtrl
 * @description # CartCtrl Controller of the demoApp
 */
angular.module('demoApp')
    .controller('CartCtrl', ['$scope', 'shoppingCart', '$http', '$location', '$rootScope', '$route', function ($scope, shoppingCart, $http, $location,$rootScope, $route) {
        $scope.shoppingCart = shoppingCart;

        $scope.updateCart = function (shoppingCart, stock) {
            $scope.shoppingCart = {};
            $scope.shoppingCart.stock = stock;
            sendRequest('PUT', 'http://localhost:8080/shoppingcart/shoppingCart/' + shoppingCart.id, $scope.shoppingCart);
        }

        $scope.deleteProduct = function (shoppingCart) {
        	sendRequest('DELETE', 'http://localhost:8080/shoppingcart/shoppingCart/' + shoppingCart.id);
        }

        $scope.clearCart = function (shoppingCart) {
         	sendRequest('DELETE', 'http://localhost:8080/shoppingcart/shoppingCart/');
        }


        $scope.purchaseProducts = function (shoppingCart) {
        	var itemsIds = [];
        	for (var i=0;i<shoppingCart.length;i++) {
        		itemsIds.push(shoppingCart[i].id);
        	}

            sendRequest('POST', 'http://localhost:8080/shoppingcart/shoppingCart/purchase/', itemsIds, '/history', function() {
            	$scope.shoppingCart = {};
            });
        }

        function sendRequest(method, url, data, routeTo, callBack) {
        	$http({
      		  method: method,
      		  url: url,
      		  data: data
      		}).then(function successCallback(response) {
      			if (typeof callBack === "function") {
      				callBack();
      			}
      			if(routeTo === undefined) {
      				$location.path('/cart');
      				$route.reload();
      			  } else {
      				$location.path(routeTo);
      			  }

      		}, function errorCallback(error) {
      			console.log('log error' + error);
      		});

        }

    }]);