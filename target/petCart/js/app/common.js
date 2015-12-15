$(document).ready(function(){
	$('#customer_rating_star_div i').on('click',
		function()
		{
			if($(this).hasClass('fa-star-o'))
			{
				$(this).removeClass('fa-star-o').addClass('fa-star').addClass('add-rating-color');
			}else
			{
				$(this).removeClass('fa-star').addClass('fa-star-o'); 
		    }
		 });
});

/* function to open sub Categories */
function openSubCategory(cat)
{
	$('#'+cat).slideToggle(300, 'linear');
		
}

/* fucntion use to open page in main div*/
function openPage(page){
	alert('nothing to load...!!!');
	
}

function showCenteredLoading(text)
{

	$.gritter.add(
	{
		title: text,
		class_name: 'gritter-info gritter-bottom-right',
		time: 500
	});

	return false;

}

function hasValue(form, fieldId)
{
	if (form == null)
		return false;
	var val = form.getValue(fieldId);
	return hasValue(val);
}

function hasValue(val)
{
	
	return (val != null && val != undefined && val != NaN && val != "NaN" && val != "null" && val != "undefined" && (val != "" || String(val) == "0") && val != "-Please select-" && val != "--");
}


/* generic function to check the ajax response */

function isAuthorize(Response, data, Request, callbackFun)
{
   try
	{
		if (hasValue(Response.responseText))
		{
			var sessionString = Response.responseText.substring(0, 30);

			if (sessionString.indexOf("<!DOCTYPE HTML>") == 0)
			{

				window.location.href = context + "/index.jsp";
				return false;
			}
		}

		if (rpcResponse.status == 0)
		{

			return false;
		}

		if (rpcResponse.status == 403)
		{
			showCenteredLoading(access_failure_message);
			return false;
		}

		if (rpcResponse.status == 500)
		{
			$(".close").click();
			openPage(page_500_html);
			return false;
		}
		else if (rpcResponse.status == 404)
		{

			$(".close").click();
			openPage(page_404_html);
			return false;
		}
		else if (rpcResponse.status == 401)
		{ /* Session expire message */
			showCenteredLoading(gettingDetails_Error);
			return false;
		}
	}
	catch (e)
	{}
	return true;
}


function sendPOSTRequest(url, jsonParams, callbackFunc, addlParam)
{
    
	$.ajax(
	{
		type: "POST",
		url: url,
		contentType: "application/json; charset=utf-8",
		dataType: "application/json",
		beforeSend: function()
		{
			//ajaxCounter = ajaxCounter + 1;
			showMainLoading(true);
			return true;
		},
		complete: function()
		{
			//ajaxCounter = ajaxCounter - 1;
			showMainLoading();
		},
		data: jsonParams,
		error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			if (isAuthorize(XMLHttpRequest, textStatus, errorThrown, url,
					callbackFunc))
			{
				eval(callbackFunc + "(arguments[0], arguments[1], arguments[2], \"" + addlParam + "\")");
			}
			else
			{
				return false;
			}
		},
		success: function(data, status, errorThrown)
		{
			if (isAuthorize(XMLHttpRequest, status, errorThrown, url,
					callbackFunc))
			{
				eval(callbackFunc + "(arguments[2], arguments[1], arguments[0], \"" + addlParam + "\")");
			}
			else
			{
				return false;
			}
		}
	});
	
}


function sendGETRequest(url, callbackFunc, addlParam, notShowLoading)
{
	
	$.ajax({type: "GET",url: url,
			beforeSend: function()
			{
			   //showMainLoading();
				return true;
			},
			complete: function()
			{
				//showMainLoading();
			},
			error: function(HttpRequest, textStatus, errorThrown)
			{
				if (isAuthorize(HttpRequest, textStatus, errorThrown,
						url, callbackFunc))
				{

					eval(callbackFunc + "(arguments[0], arguments[2].responseText, arguments[2], \"" + addlParam + "\")");
				}
				else
				{
					return false;
				}
			},
			success: function(data, status, request)
			{

				if (isAuthorize(request, status, data, url, callbackFunc))
				{
					eval(callbackFunc + "(arguments[2], arguments[0], arguments[1], \"" + addlParam + "\")");
				}
				else
				{
					return false;
				}
			}
		});
	}


function statuscheck(statuscode, tablename)
{
	if (statuscode == 500)
	{

		return false;
	}

	else if (statuscode == 404)
	{

		return false;

	}

	return true;

}
