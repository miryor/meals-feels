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
    <li class="active"><a id="meal-chart-tab" href="#chartmeal" data-toggle="tab">Chart</a></li>
    <li><a id="meals-tab" href="#meals" data-toggle="tab">Meals</a></li>
    <li><a id="add-meal-tab" href="#addmeal" data-toggle="tab">Add Meal</a></li>
    <li><a id="pains-tab" href="#pains" data-toggle="tab">Pain</a></li>
    <li><a id="add-pain-tab" href="#addpain" data-toggle="tab">Add Pain</a></li>
</ul>

<div class="tab-content">
 
<div id="meals" class="tab-pane">
    <a name="meals"></a>
    
    <table border="1">
    	<tr>
    		<th>Date</th>
    		<th>Meal Type</th>
    		<th>Name</th>
    		<th>Gluten</th>
    		<th>Brand</th>
    		<td></td>
    	</tr>
        <c:forEach var="meal" items="${mealList}">
            <tr>
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

<div id="pains" class="tab-pane">
    <a name="pains"></a>
    
    <table border="1">
    	<tr>
    		<th>Date</th>
    		<th>Gas</th>
    		<th>Bloating</th>
    		<th>Abdominal</th>
    		<th>Burning</th>
    		<th>Headache</th>
    		<th>Lethargy</th>
    		<th>Joints</th>
    	</tr>
        <c:forEach var="pain" items="${painList}">
            <tr>
                <td class="painDateCell">${pain.entryDate}</td>
                <td class="gasCell">${pain.gas}</td>
                <td class="bloatingCell">${pain.bloating}</td>
                <td class="abdominalCell">${pain.abdominal}</td>
                <td class="burningCell">${pain.burning}</td>
                <td class="headacheCell">${pain.headache}</td>
                <td class="lethargyCell">${pain.lethargy}</td>
                <td class="jointsCell">${pain.joints}</td>
                <td><input type="button" value="delete" onclick="window.location='pain/delete?id=${pain.id}'"/></td>
            </tr>
        </c:forEach>
    </table>  
</div>

<div id="addpain" class="tab-pane">
	<a name="addpain"></a>
    
        <form action="pain/save" method="post">
            <input type="hidden" name="id">
            <label for="painEntryDate">Pain Date</label> <input type="text" id="painEntryDate" name="entryDate"/>
            <label for="gas">Gas</label> <select id="gas" name="gas">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="bloating">Bloating</label> <select id="bloating" name="bloating">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="abdominal">Abdominal</label> <select id="abdominal" name="abdominal">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="burning">Burning</label> <select id="burning" name="burning">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="headache">Headache</label> <select id="headache" name="headache">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="lethargy">Lethargy</label> <select id="lethargy" name="lethargy">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <label for="joints">Joints</label> <select id="joints" name="joints">
            	<option value="None">None</option>
            	<option value="Sparse">Sparse</option>
            	<option value="Some">Some</option>
            	<option value="Definitely">Definitely</option>
            	<option value="A Lot">A Lot</option>
            	</select>
            <input type="submit" value="Submit"/>
        </form>
</div>

<div id="chartmeal" class="tab-pane active">
    <a name="chartmeal"></a>
	<div id="legendcontainer" style="width:760px;position:"></div>
	<div id="placeholder" style="width:760px;height:400px;"></div>
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
	else { console.log( "not sure: " + containsGlute ); return 0; }
}
function painToNumber( pain ) {
	console.log( pain );
	if ( pain == 'None' ) return 0;
	else if ( pain == 'Sparse' ) return 1;
	else if ( pain == 'Some' ) return 2;
	else if ( pain == 'Definitely' ) return 3;
	else if ( pain == 'A Lot' ) return 4;
	else { console.log( "not sure: " + pain ); return 0; }	
}

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

var painEntryDates = $(".painDateCell").map(function() { return $(this).text(); }).get();
var gas = $(".gasCell").map(function() { return $(this).text();	}).get();
var burning = $(".burningCell").map(function() { return $(this).text();	}).get();
var bloating = $(".bloatingCell").map(function() { return $(this).text();	}).get();
var abdominal = $(".abdominalCell").map(function() { return $(this).text();	}).get();
var headache = $(".headacheCell").map(function() { return $(this).text();	}).get();
var lethargy = $(".lethargyCell").map(function() { return $(this).text();	}).get();
var joints = $(".jointsCell").map(function() { return $(this).text();	}).get();
var gasDataSet = [];
var burningDataSet = [];
var bloatingDataSet = [];
var abdominalDataSet = [];
var lethargyDataSet = [];
var headacheDataSet = [];
var jointsDataSet = [];
for ( var i = 0; i < painEntryDates.length; i++ ) {
	gasDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( gas[i] ) ] );
	burningDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( burning[i] ) ] );
	bloatingDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( bloating[i] ) ] );
	abdominalDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( abdominal[i] ) ] );
	lethargyDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( lethargy[i] ) ] );
	headacheDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( headache[i] ) ] );
	jointsDataSet.push( [ entryDateToJS( painEntryDates[i] ), painToNumber( joints[i] ) ] );
}

var hour = 60*60*1000;
function plotByChoice(doAll) {
	
var datasets ={
	"Meals" :{ label : "Meals", data: mealDataSet, lines: { show: true }, points: { show: true } },
	"Gas" :{ label : "Gas", data: gasDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Burning" :{ label : "Burning", data: burningDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Bloating" :{ label : "Bloating", data: bloatingDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Abdominal" :{ label : "Abdominal", data: abdominalDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Headache" :{ label : "Headache", data: headacheDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Lethargy" :{ label : "Lethargy", data: lethargyDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
	"Joints" :{ label : "Joint Ache", data: jointsDataSet, lines: { show: false }, bars: { show: true, barWidth: hour } },
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
    $('#legend .legendCB').each( function() {
    	console.log( this.id );
		if (this.checked) data.push(datasets[this.id]);
		else data.push({label: this.id, data: []});
    } );
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
	},
    grid: {
        hoverable: true
    }
});
$('#legend').find("input").click(function(){setTimeout(plotByChoice,100);});
// $('#legend .legendCB').change(function(){setTimeout(plotByChoice,1000);});
}

function showTooltip(x, y, contents) {
    $('<div id="tooltip">' + contents + '</div>').css({
        position: 'absolute',
        display: 'none',
        top: y + 5,
        left: x + 5,
        border: '1px solid #fdd',
        padding: '2px',
        'background-color': '#fee',
        opacity: 0.80
    }).appendTo("body").fadeIn(200);
}

var previousPoint = null;
$("#placeholder").bind("plothover", function(event, pos, item) {
    $("#x").text(pos.x.toFixed(2));
    $("#y").text(pos.y.toFixed(2));

    if (item) {
        if (previousPoint != item.datapoint) {
            previousPoint = item.datapoint;

            $("#tooltip").remove();
            var x = item.datapoint[0].toFixed(2),
                y = item.datapoint[1].toFixed(2),
                //set default content for tooltip
                content = item.series.label + " of " + x + " = " + y;
            
            //now show tooltip
            showTooltip(item.pageX, item.pageY, content);
        }
    }
    else {
        $("#tooltip").remove();
        previousPoint = null;
    }

});


plotByChoice(this);

$(function() {
	$( "#entryDate" ).datetimepicker( { format: 'm/d/Y H:i'} );
	$( "#painEntryDate" ).datetimepicker( { format: 'm/d/Y H:i'} );
});
</script>
</body>
</html>