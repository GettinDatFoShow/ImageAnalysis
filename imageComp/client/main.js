import { Template } from 'meteor/templating';
import { ReactiveVar } from 'meteor/reactive-var';
// import '../imports/ui/body.js';
import './main.html';

if (Meteor.isClient) {
    Meteor.startup(function() {
        Session.setDefault("templateName", "home");
    })
}

// Router.route('/', function () {
//   this.render('Home', {
//     data: function () { return Items.findOne({_id: this.params._id}); }
//   });
// });

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


// ******* moved body.js file here ***********

Template.choice.events({
  'click button'(event, instance) {

  }});
// Router.route('/login', {
//     name: 'login',
//     template: 'login'
// });
