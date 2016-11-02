import {
    Template
} from 'meteor/templating';
import {
    ReactiveVar
} from 'meteor/reactive-var';
// import '../imports/ui/body.js';
import './main.html';
import * as RGBAnalyse from 'rgbanalyse';


if (Meteor.isClient) {
    Meteor.startup(function() {
        Session.setDefault("templateName", "home");
    })
}


if (Meteor.isServer) {
    // server code goes here
}

Router.configure({
    layoutTemplate: 'main'
});

Router.route('/', {
    name: 'home',
    template: 'home'
});

Router.route('/sidebyside', {
    name: 'sidebyside',
    template: 'sidebyside'
});

Router.route('/folder', {
    name: 'folder',
    template: 'folder'
});

Router.route('/weblink', {
    name: 'weblink',
    template: 'weblink'
});

Router.route('/sianalyse', {
    name: 'sianalyse',
    template: 'sianalyse'
});

Router.route('/UML_Prototype',{
  name: 'UML_Prototype',
  template: 'UML_Prototype'
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
    'click #gotToSIA': function() {
        Router.go('/sianalyse');
    }
})

Template.returnHome.events({
    'click #goToHome': function() {
        Router.go('/');
    }
})

// in work *
Template.analyseSingle.events({
    'click #rgbanalyse': function() {
      // var img = './UML_Prototype.jpg';
      //analyse(img);
      buildImage('./UML_Prototype.jpg');
    }
})

/*########################    FUNCTIONS     ############################################*/

function analyse(img) {
  RGBAnalyse.analyse(img, { neutrals: 20, smoothing: 25, distance: 20 }, function(err, data) {
    var bin = img.parentNode,
        div = document.createElement("div"),
        viz;

    viz = new Image();
    viz.src = data.visualization.histogram;
    viz.setAttribute("class", "histogram visualisation");
    addVisualization(div, viz, "rgb histogram");

    var dominant = data.analysis.hsl.dominant;
    dominant.forEach(function(color, idx) {
      viz = document.createElement("hr");
      var rgb = RGBAnalyse.computeRGB(color.H)
      viz.style.background = "rgb("+rgb.r+","+rgb.g+","+rgb.b+")";
      addVisualization(div, viz, idx===0 ? "major hues" : false);
    });
// Data View
    viz = new Image();
    viz.src = data.visualization.spectrum.spectogram;
    viz.setAttribute("class", "spectogram visualisation");
    addVisualization(div, viz, "spectrogram");

    viz = new Image();
    viz.src = data.visualization.spectrum.histogram;
    viz.setAttribute("class", "spectral histogram visualisation");
    addVisualization(div, viz, "spectral histogram");

    bin.appendChild(div);

    img.onmouseover = function() {
      bin.replaceChild(data.analysis.lab, img);
    };

    data.analysis.lab.onmouseout = function() {
      bin.replaceChild(img, data.analysis.lab);
    };
  });
};

function addVisualization(container, element, text) {
   if(text) {
     var label = document.createElement("h2");
     label.textContent = text;
     container.appendChild(label);
   }
   container.appendChild(element);
 }

 function buildImage(idx) {
  //  idx = (idx<10) ? '0' + idx : idx;
   var img = new Image();
   img.setAttribute("class", "demo");
   var div = document.createElement("div");
   div.appendChild(img);
   img.onload = function(evt) {
     var img = evt.target;
     setTimeout(function() {
       analyse(img);
     }, 100);
   };
   img.src = idx;
   document.getElementById("images").appendChild(div);
 }

 // var imgcount = 17;
 // for(var i=1; i<=imgcount; i++) { buildImage(i); }


//**************** js commands/functions ****************
// command for asking for imput:
// var visitorInput = prompt("plese enter some input");
// command for writing to the screen:
// document.write("This will apear!");
// command to log information:
// console.log("The Program is running!");
