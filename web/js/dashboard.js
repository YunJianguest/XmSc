Plot_init=function(uploads,downloads,max){
	
	"use strict";
	
	function showTooltip(x, y, contents) {
		jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css( {
		  position: 'absolute',
		  display: 'none',
		  top: y + 5,
		  left: x + 5
		}).appendTo("body").fadeIn(200);
	}
    
   
   var label1="支出";
   var label2="收入"; 
   /*if(state==1){
	   label1="本年";
	   label2="上年";
   }*/
	var plot = jQuery.plot(jQuery("#basicflot"),
		[{ data: uploads,
         label: label1,
         color: "#1CAF9A"
        },
        { data: downloads,
          label: label2,
          color: "#428BCA"
        }
      ],
      {
			series: {
				lines: {
					show: false
				},
				splines: {
					show: true,
					tension: 0.5,
					lineWidth: 1,
					fill: 0.45
				},
				shadowSize: 0
			},
			points: {
				show: true
			},
		  legend: {
          position: 'nw'
        },
		  grid: {
          hoverable: true,
          clickable: true,
          borderColor: '#ddd',
          borderWidth: 1,
          labelMargin: 10,
          backgroundColor: '#fff'
        },
		  yaxis: {
          min: 0,
          max: max,
          color: '#eee'
        },
        xaxis: {
          color: '#eee'
        }
		});
		
	 var previousPoint = null;
	 jQuery("#basicflot").bind("plothover", function (event, pos, item) {
      jQuery("#x").text(pos.x.toFixed(2));
      jQuery("#y").text(pos.y.toFixed(2));
			
		if(item) {
		  if (previousPoint != item.dataIndex) {
			 previousPoint = item.dataIndex;
						
			 jQuery("#tooltip").remove();
			 var x = item.datapoint[0].toFixed(2),
			 y = item.datapoint[1].toFixed(2);
	 			
			 showTooltip(item.pageX, item.pageY,
				  item.series.label + " of " + x + " = " + y);
		  }
			
		} else {
		  jQuery("#tooltip").remove();
		  previousPoint = null;            
		}
		
	 });
		
	 jQuery("#basicflot").bind("plotclick", function (event, pos, item) {
		if (item) {
		  plot.highlight(item.series, item.datapoint);
		}
	 });
    
    // Donut Chart
   
	
	// Trigger Resize in Morris Chart
   var delay = (function() {
		var timer = 0;
		return function(callback, ms) {
			clearTimeout(timer);
			timer = setTimeout(callback, ms);
		};
    })();

   jQuery(window).resize(function() {
		delay(function() { 
	}, 200);
   }).trigger('resize');
    
   jQuery('#sparkline').sparkline([4,3,3,1,4,3,2,2,3,10,9,6], {
		  type: 'bar', 
		  height:'30px',
        barColor: '#428BCA'
   });
	
    
    jQuery('#sparkline2').sparkline([9,8,8,6,9,10,6,5,6,3,4,2], {
		  type: 'bar', 
		  height:'30px',
        barColor: '#999'
    });
    
    // Chosen Select
     
	 
	
	// Do not use the code below. It's for demo purposes only
	var c = jQuery.cookie('change-skin');
   if (jQuery('.panel-stat').length > 0 && c == 'dodgerblue') {
      jQuery('.panel-stat').each(function(){
         if ($(this).hasClass('panel-danger')) {
            $(this).removeClass('panel-danger').addClass('panel-warning');
         }
      });
   }
	
	
    
}
