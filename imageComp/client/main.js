import { Template } from 'meteor/templating';
import { ReactiveVar } from 'meteor/reactive-var';
// import '../imports/ui/body.js';
import './main.html';

if (Meteor.isClient) {
    Meteor.startup(function() {
        Session.setDefault("templateName", "home");
    })
}

if(Meteor.isServer){
    // server code goes here
}

Router.configure({
    layoutTemplate: 'main'
});

Router.route('/',{
    name: 'home',
    template: 'home'
});

Router.route('/sidebyside',{
    name: 'sidebyside',
    template: 'sidebyside'
});

Router.route('/folder',{
    name: 'folder',
    template: 'folder'
});

Router.route('/weblink', {
    name: 'weblink',
    template: 'weblink'
});

Router.route('/dataView', {
    name: 'dataView',
    template: 'dataView'
});

// Button Fucntions ************************

Template.choice1.events({
  'click #gotToSBS': function() {
    Router.go('/sidebyside');
  }
})

Template.choice2.events({
  'click #gotToIFA': function() {
    Router.go('/folder');
  }
})

Template.choice3.events({
  'click #gotToWL': function() {
    Router.go('/weblink');
  }
})

Template.choice4.events({
  'click #gotToDV': function() {
    Router.go('/dataView');
  }
})

Template.returnHome.events({
  'click #goToHome': function() {
    Router.go('/');
  }
})
