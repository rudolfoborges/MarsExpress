'use strict';

var gulp = require('gulp'),
	concat = require('gulp-concat'),
	htmlmin = require('gulp-htmlmin'),
	templateCache = require('gulp-angular-templatecache'),
	sass = require('gulp-sass'),
	imagemin = require('gulp-imagemin');
	

gulp.task('angular', function(){
	return gulp
			.src('src/main/resources/angular/**/*.js')
			.pipe(concat('angular.app.js'))
			.pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('templates', function () {
	  return gulp.src('src/main/resources/angular/views/**/*.html')
	  	.pipe(htmlmin({collapseWhitespace: true}))
	    .pipe(templateCache('angular.views.js', { module:'angular.me.views', standalone:true }))
	    .pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('sass', function(){
	  return gulp.src('src/main/resources/static/assets/css/*.scss')
	  	.pipe(sass())
	    .pipe(gulp.dest('src/main/resources/static/assets/css'));
});

gulp.task('images', function() {
	return gulp.src('src/main/resources/normal-images/*')
		.pipe(imagemin({
			progressive: true
		}))
		.pipe(gulp.dest('src/main/resources/static/assets/images'));
});

gulp.task('watch-js', function() {
    return gulp.watch('src/main/resources/angular/**/*.js', ['angular']);
});

gulp.task('watch-html', function() {
    return gulp.watch('src/main/resources/angular/views/**/*.html', ['templates']);
});

gulp.task('watch-sass', function() {
    return gulp.watch('src/main/resources/static/assets/css/**/*.scss', ['sass']);
});

gulp.task('watch-images', function() {
    return gulp.watch('src/main/resources/normal-images/**/*', ['images']);
});

gulp.task('default', ['sass', 'angular', 'templates', 'images', 'watch-sass', 'watch-js', 'watch-js', 'watch-html', 'watch-images']);