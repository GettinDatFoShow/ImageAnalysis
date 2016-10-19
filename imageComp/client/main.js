import { Template } from 'meteor/templating';
import { ReactiveVar } from 'meteor/reactive-var';
import '../imports/ui/body.js';
import './main.html';

if (Meteor.isClient) {
    Meteor.startup(function() {
        Session.setDefault("templateName", "main");
    })
}

Router.route('/',{
    name: 'home',
    template: 'home',
});

Router.route('/sidebyside',{
    name: 'sidebyside',
    template: 'sidebyside'
});
Router.route('/folder',{
    name: 'folder',
    template: 'folder'
});
Router.route('/login', {
    name: 'login',
    template: 'login'
});

Router.route('/weblink', {
    name: 'weblink',
    template: 'weblink'
});
S
Router.configure({
    layoutTemplate: 'main'
});
