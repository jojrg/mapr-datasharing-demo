var app = angular.module('crudApp',['ngMaterial', 'ngMessages', 'material.svgAssetsCache','ui.router','ngStorage','md.data.table']);

console.log("mdApp.js::crudApp");

//TODO remove constants
app.constant('urls', {
    BASE: 'http://localhost:9000/SpringBootCRUDApp',
    USER_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/user/',
    RULE_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/rule/',
    FIELDNAMES_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/config/fieldnames/',
    DATAFILTERNAMES_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/config/datafilternames/'
});

app.config(function($mdThemingProvider) {

  // Extend the red theme with a different color and make the contrast color black instead of white.
  // For example: raised button text will be black instead of white.
  var neonRedMap = $mdThemingProvider.extendPalette('red', {
  //  '500': '#ff0000'
   '500': '#c8202f'
  });

  // Register the new color palette map with the name <code>neonRed</code>
  $mdThemingProvider.definePalette('neonRed', neonRedMap);

  // Use that theme for the primary intentions
  $mdThemingProvider.theme('default')
    .primaryPalette('neonRed');

});


