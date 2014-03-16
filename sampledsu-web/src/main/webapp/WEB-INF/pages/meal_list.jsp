<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Meals</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.css" rel="stylesheet">
    <link href="//code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css" rel="stylesheet">
    <link href="js/jquery.datetimepicker.css" rel="stylesheet">

    <!--
    IMPORTANT:
    This is Heroku specific styling. Remove to customize.
    -->
    <link href="http://heroku.github.com/template-app-bootstrap/heroku.css" rel="stylesheet">
    <style type="text/css">
        .instructions {
            display: none;
        }

        .instructions li {
            margin-bottom: 10px;
        }

        .instructions h2 {
            margin: 18px 0;
        }

        .instructions blockquote {
            margin-top: 10px;
        }

        .screenshot {
            margin-top: 10px;
            display: block;
        }

        .screenshot a {
            padding: 0;
            line-height: 1;
            display: inline-block;
            text-decoration: none;
        }

        .screenshot img, .tool-choice img {
            border: 1px solid #ddd;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075);
        }
    </style>
    <!-- /// -->
    <script type="text/javascript">
        <!--
        function appname() {
            return location.hostname.substring(0, location.hostname.indexOf("."));
        }

        function openRestClient(startUrl) {
            $('#rest-url').val(startUrl);
            $('#rest-result-container').hide();
            $('#rest-client-tab').click();
        }

        function sendGetRequest() {
            $.ajax({
                url:$('#rest-url').val(),
                dataType:'json',
                context:document.body,
                success:function (data, textStatus, jqXHR) {
                    showRestResults('Success', 'alert-success', JSON.stringify(data, undefined, 2));
                },
                error:function (qXHR, textStatus, errorThrown) {
                    if (errorThrown instanceof SyntaxError) {
                        errorThrown = errorThrown.message;
                    }

                    showRestResults(textStatus, 'alert-error', errorThrown);
                }
            });
        }

        function showRestResults(headerText, headerClass, bodyText) {
            $('#rest-result-header-text').text(headerText);
            $('#rest-result-header').removeClass('alert-error').removeClass('alert-success');
            $('#rest-result-header').addClass(headerClass);

            $('#rest-result-body').text(bodyText);
            prettyPrint();

            $('#rest-result-container').show();
        }

        function prettyPrintOnce() {
            prettyPrint();
            $('.prettyprint.once').removeClass('prettyprint');
        }
        // -->
    </script>
</head>

<body onload="prettyPrintOnce();">

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a href="/meal" class="brand">Meals</a>
        </div>
    </div>
</div>

<div class="container" id="getting-started">
<div class="row">
<div class="span8 offset2">

<div class="page-header">
    <h1>Meals</h1>
</div>

<ul id="tab" class="nav nav-tabs">
    <li class="active"><a id="meals-tab" href="#meals" data-toggle="tab">Meals</a></li>
    <li><a id="add-meal-tab" href="#addmeal" data-toggle="tab">Add Meal</a></li>
    <li><a id="meal-chart-tab" href="#chartmeal" data-toggle="tab">Chart</a></li>
</ul>

<div class="tab-content">
 
<div id="meals" class="instructions tab-pane active">
    <a name="meals"></a>
    
    <table border="1">
        <c:forEach var="meal" items="${mealList}">
            <tr>
                <td>${meal.user}</td>
                <td>${meal.createdDate}</td>
                <td>${meal.updateDate}</td>
                <td class="entryDate">${meal.entryDate}</td>
                <td>${meal.mealType}</td>
                <td>${meal.mealName}</td>
                <td class="containsGlutenCell">${meal.containsGluten}</td>
                <td>${meal.brand}</td>
                <td><input type="button" value="delete" onclick="window.location='meal/delete?id=${meal.id}'"/></td>
            </tr>
        </c:forEach>
    </table>  
</div>

<div id="addmeal" class="tab-pane">
	<a name="addmeal"></a>
    
        <form action="meal/save" method="post">
            <input type="hidden" name="id">
            <label for="entryDate">Meal Date</label> <input type="text" id="entryDate" name="entryDate"/>
            <label for="mealName">Name of Meal</label> <input type="text" id="mealName" name="mealName"/>
            <label for="mealType">Type of Meal</label> <select id="mealType" name="mealType">
            	<option value="Breakfast">Breakfast</option>
            	<option value="Lunch">Lunch</option>
            	<option value="Dinner">Dinner</option>
            	<option value="Snack">Snack</option></select>
            <label for="containsGluten">Contains Gluten</label> <select id="containsGluten" name="containsGluten">
            	<option value="None">None</option>
            	<option value="Not Sure">Not Sure</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	</select>
            <label for="brand">Brand</label> <input type="text" id="brand" name="brand"/>
            <input type="submit" value="Submit"/>
        </form>
</div>

<div id="chartmeal" class="tab-pane">
    <a name="chartmeal"></a>
	<div id="legendcontainer" style="width:1100px;position:"></div>
	<div id="placeholder" style="width:1100px;height:400px;"></div>
	<div id="legend" style="width:600px;height:300;"></div>
</div>



<!-- end tab content -->
</div>


</div>


</div>

</div>
</div>
</div>

<script src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="//code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/prettify/r224/prettify.min.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
<script src="js/jquery.flot.min.js"></script>
<script src="js/jquery.flot.time.min.js"></script>
<script type="text/javascript">
$('#legend .legendCB').click(function(){ 
    plotByChoice(true);
});
//Setup labels for use on the Y-axis  
var tickLabels = [[0, 'None'], [1, 'Maybe'], [2, 'Some'], [3, 'Definitely'], [4, 'A Lot']];  

var DataSet1 = [ 
             [1356998400000, 2.4],
            [1357084800000, 3.4 ],
            [1357171200000, 4.5 ],
            [1357257600000, 5 ],
            [1357344000000,  5],
           
];

var firstTime =true;

function entryDateToJS( entryDate ) {
	var d = Date.parse( entryDate );
	console.log( entryDate + ' = ' + d );
	return d;
}
function containsGlutenToNumber( containsGluten ) {
	console.log( containsGluten );
	if ( containsGluten == 'None' ) return 0;
	else if ( containsGluten == 'Maybe' ) return 1;
	else if ( containsGluten == 'Some' ) return 2;
	else if ( containsGluten == 'Definitely' ) return 3;
	else if ( containsGluten == 'A Lot' ) return 4;
	else return 5;
}
function plotByChoice(doAll) {
	
	var entryDates = $(".entryDate").map(function() {
	    return $(this).text();
	}).get();
	var containsGlutens = $(".containsGlutenCell").map(function() {
	    return $(this).text();
	}).get();
	var mealDataSet = [];
	for ( var i = 0; i < entryDates.length; i++ ) {
		mealDataSet.push( [ entryDateToJS( entryDates[i] ), containsGlutenToNumber( containsGlutens[i] ) ] );
	}
	
var datasets ={
	"Meals" :{
	  label : "Meals",
	  data: mealDataSet
	},
};
	
data = [];
if (doAll != null)
{
    $.each(datasets, function(key, val) {
        data.push(val);
    });
}   
else
{
    $('#legend .legendCB').each(
        function(){
            if (this.checked)
            {         
                 data.push(datasets[this.id]);
            }
            else
            {
                 data.push({label: this.id, data: []})
            }        
        }
    );
}        

$.plot($("#placeholder"), data, { 
	xaxis: {
	mode: "time", timeformat: "%m/%d/%y", minTickSize: [1, "day"]
	},
	yaxes: [ { min: 0, ticks: tickLabels  }, {
			// align if we are to the right
			alignTicksWithAxis: "right",
			position: "right",
			min :-25,
			max:50 
		}
	],
	// legend: { labelBoxBorderColor: "#0200f0", container: $("#legendcontainer"), noColumns: 2},
	legend: {
		container: $("#legend"),
    	labelFormatter: function (label, series) {
	        var cb = '<input class="legendCB" type="checkbox" ';
	        if (series.data.length > 0) {
	            cb += 'checked="true" ';
	        }
	        cb += 'id="' + label + '" /> ';
	        cb += label;
	        return cb;
	    }
	}
});
$('#legend').find("input").click(function(){setTimeout(plotByChoice,100);});
// $('#legend .legendCB').change(function(){setTimeout(plotByChoice,1000);});

}

plotByChoice(this);

$(function() {
	$( "#entryDate" ).datetimepicker( { format: 'm/d/Y H:i'} );
});
</script>
</body>
</html>