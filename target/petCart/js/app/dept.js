/* function to get navbar data  */
function loadNavBarData()
{
	sendGETRequest(GET_ALL_CATEGORIES_URL,"addDepartmentCallBack","");
}

/* callback function to get navbar data  */
function addDepartmentCallBack(HttpRequest,data,Request)
{
	 if (statuscheck(HttpRequest.status)) {
		  if (HttpRequest.status == 200) {
			   if(hasValue(data))
                {
                	appendDataInNabBar(data);
				}					
            } else {
                showCenteredLoading(ERROR_IN_GET);
               
            }
        
    }
}

/* function to append navbar data  */
function appendDataInNabBar(data)
{
	console.log(data);

}




