import { Template } from 'meteor/templating';

import './body.html';

Template.body.helpers({

  tasks: [

    { text: 'Side by Side Comparison' },

    { text: 'Image Folder Analysis' },

    { text: 'Web Link Image' },
  ],
});
