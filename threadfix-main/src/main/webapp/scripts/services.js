var threadfixModule = angular.module('threadfix')

threadfixModule.factory('tfEncoder', function($rootScope, $location) {

    var tfEncoder = {};

    tfEncoder.encode = function(path) {
        return $rootScope.urlRoot + path + $rootScope.csrfToken;
    }

    tfEncoder.encodeRelative = function(path) {
        return $location.path() + path + $rootScope.csrfToken;
    }

    return tfEncoder;
});

threadfixModule.factory('threadfixAPIService', function($location, $http, tfEncoder, $rootScope) {

    var threadfixAPIService = {};

    threadfixAPIService.getTeams = function() {
        return $http({
            method: 'GET',
            url: tfEncoder.encode('/organizations/jsonList')
        });
    };

    threadfixAPIService.loadAppTableReport = function(id) {
        var url = '/organizations/' + id + '/getReport';

        return $http({
            method: 'GET',
            url: tfEncoder.encode(url)
        });
    };

    threadfixAPIService.loadReport = function(url, query) {
        return $http({
            method: 'GET',
            url: tfEncoder.encode(url) + query
        });
    };

    threadfixAPIService.loadVulns = function() {
        return $http({
            method: 'GET',
            url: $location.path() + "/vulns" + $rootScope.csrfToken
        });
    }

    return threadfixAPIService;
});

threadfixModule.factory('threadFixModalService', function($http) {

        var threadFixModalService = {};

        threadFixModalService.post = function(url, data) {
            return $http({
                method: 'POST',
                url: url,
                data : data,
                contentType : "application/x-www-form-urlencoded",
                dataType : "text"
            });
        };

        return threadFixModalService;
    });

threadfixModule.factory('focus', function ($rootScope, $timeout) {
    return function(name) {
        $timeout(function (){
            $rootScope.$broadcast('focusOn', name);
        });
    }
});