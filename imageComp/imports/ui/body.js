import { Template } from 'meteor/templating';

import './body.html';

// Template.body.helpers({
//
//   tasks: [
//
//     { text: 'Side by Side Comparison' },
//
//     { text: 'Image Folder Analysis' },
//
//     { text: 'Web Link Image' },
//   ],
// });

Template.choice.events({
  'click button'(event, instance) {


  }});
// Template.choice.onCreated(function choiceOnCreated() {
//   return;
// });
//
// Template.choice.helpers({
//   counter() {
//     return Template.instance().counter.get();
//   },
// });

// Template.choice.events({
//   'click button'(event, instance) {
//     // increment the counter when button is clicked
//     instance.counter.set(instance.counter.get() + 1);
//   },
// });
