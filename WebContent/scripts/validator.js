/**
 * Form Validation
 *@author Carlos Pinho
 */

function validate(){
	
	var name, phone;
	
	 name = formContact.name.value;
	 phone = formContact.phone.value;

	if(name === ""){
		
		alert("Fulfill the madatory Name Field!");
		//Focus on Respective Text-Missing Box
		formContact.name.focus();
		return false;
		
	}
	else if (phone === ""){
		
		alert("Fulfill the madatory Phone Field!");
		//Focus on Respective Text-Missing Box
		formContact.phone.focus();
		return false;
	}
	else{
		/* Submit Form */
		document.forms["formContact"].submit();
	}
	

	
}