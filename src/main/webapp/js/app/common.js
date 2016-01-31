maxlimitofpagination=10;

/*******function to check value in given variable********/
function exist(val)
{
	return (val != null && val != undefined && val != NaN && val != "NaN" && val != "null" && val != "undefined" && (val != "" || String(val) == "0") && val != "-Please select-" && val != "--");
}