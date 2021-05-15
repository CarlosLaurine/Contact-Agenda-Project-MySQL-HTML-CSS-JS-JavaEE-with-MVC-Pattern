/**
 *Confirmation of a Contact Deletion Solicitation
 *@author Carlos Pinho 
 */

function confirmContact(conid){
	
	let answer = confirm("Are you sure you wish to DELETE this Contact?")
	if(answer==true){
		
		//Id Receipt Test
		//alert(conid)
		
		window.location.href = "delete?id =" + conid
	}
}