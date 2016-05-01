'use strict';

var gulp = require('gulp'),
	concat = require('gulp-concat'),
	uglify = require('gulp-uglify'),
	htmlmin = require('gulp-htmlmin'),
	templateCache = require('gulp-angular-templatecache');
	

gulp.task('angular', function(){
	return gulp
			.src('src/main/resources/angular/**/*.js')
			.pipe(concat('angular.app.js'))
			.pipe(uglify())
			.pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('templates', function () {
	  return gulp.src('src/main/resources/angular/views/**/*.html')
	  	.pipe(htmlmin({collapseWhitespace: true}))
	    .pipe(templateCache('angular.views.js', { module:'angular.me.views', standalone:true }))
	    .pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('watch-js', function() {
    return gulp.watch('src/main/resources/angular/**/*.js', ['angular'])
});

gulp.task('watch-html', function() {
    return gulp.watch('src/main/resources/angular/views/**/*.html', ['templates'])
});

gulp.task('default', ['angular', 'templates', 'watch-js', 'watch-js', 'watch-html']);