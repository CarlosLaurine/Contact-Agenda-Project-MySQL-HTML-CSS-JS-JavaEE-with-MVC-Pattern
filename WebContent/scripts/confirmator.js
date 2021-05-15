/**
 *Confirmation of a Contact Deletion Solicitation
 *@author Carlos Pinho 
 */

function confirmContact(conid){
	
	//Console PopUp Message to assure the Deletion was not originated by an Accidental Click
	
	let answer = confirm("Are you sure you wish to DELETE this Contact?")
	if(answer==true){
		
		//Id Receipt Test
		//alert(conid)
		
		
		//Redirecting to Servlet with Delete Action plus the set selected Id Parameter 
		window.location.href = "delete?conId=" + conid
	}
}