var myApp=angular.module("myApp",["ngRoute"]);

myApp.config(function($routeProvider)
{
	$routeProvider.when("/",{templateUrl:"template/home.html"})
	           .when("/home",{templateUrl:"template/home.html"})
	           .when("/blog",{templateUrl:"c_blog/blog.html"})
	           .when("/forum",{templateUrl:"template/forum.html"})
	           .when("/job",{templateUrl:"template/job.html"})
	           .when("/login",{templateUrl:"template/login.html"})
	           .when("/register",{templateUrl:"template/register.html"})
	           .when("/updateblog",{templateUrl:"c_blog/updateblog.html"})
});